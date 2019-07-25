package boomerang.example;

import java.util.function.Function;

import static boomerang.example.TestUtils.*;

public class OdefaTwoLevelPairmapBool {

    public static void main(String... args) {
        boolean t_value = true;
        boolean f_value = false;

        MyBoolean t = new MyBoolean(t_value);
        MyBoolean f = new MyBoolean(f_value);

        Pair<MyBoolean, MyBoolean> pair_first = new Pair<>(t, f);
        Pair<MyBoolean, MyBoolean> pair_second = new Pair<>(f, t);

        Pair<Pair<MyBoolean, MyBoolean>, Pair<MyBoolean, MyBoolean> > pair_two =
                new Pair<>(pair_first, pair_second);

        Function<MyBoolean, MyBoolean> inc_fun =
                ((MyBoolean n) ->
                {
                    boolean val = n.getValue();
                    if (val == true) {
                        boolean new_false_val = false;
                        MyBoolean new_false = new MyBoolean(new_false_val);
                        return new_false;
                    }
                    {
                        boolean new_true_val = true;
                        MyBoolean new_true = new MyBoolean(new_true_val);
                        return new_true;
                    }
                }
                );

        Function<Function<MyBoolean, MyBoolean>, Function<Pair<MyBoolean, MyBoolean>, Pair<MyBoolean, MyBoolean>>>
                pmlv1_bool_to_bool = pairMap();

        Function<Function<Pair<MyBoolean, MyBoolean>, Pair<MyBoolean, MyBoolean>>,
                Function<Pair<Pair<MyBoolean, MyBoolean>, Pair<MyBoolean, MyBoolean>>,
                        Pair<Pair<MyBoolean, MyBoolean>, Pair<MyBoolean, MyBoolean>>>> pmlv2_bool_to_bool = pairMap();

        Function<Pair<MyBoolean, MyBoolean>, Pair<MyBoolean, MyBoolean>> one_pmap =
                pmlv1_bool_to_bool.apply(inc_fun);

        Function<Pair<Pair<MyBoolean, MyBoolean>, Pair<MyBoolean, MyBoolean>>,
                Pair<Pair<MyBoolean, MyBoolean>, Pair<MyBoolean, MyBoolean>>> two_pmap =
                pmlv2_bool_to_bool.apply(one_pmap);

        Pair<Pair<MyBoolean, MyBoolean>, Pair<MyBoolean, MyBoolean>> res_nested_pair =
                two_pmap.apply(pair_two);

        Pair<MyBoolean, MyBoolean> res_pair = res_nested_pair.getFirst();

        MyBoolean res = res_pair.getSecond();

        queryFor(res);

    }

    private static <T> void queryFor(T query) {

    }

    private static <T, U> Function<Function<T,U>, Function<Pair<T,T>, Pair<U,U>>> pairMap () {
        Function<Function<T,U>, Function<Pair<T,T>, Pair<U,U>>> pairmap_fun =
                (Function<T,U> fun) -> {
                    Function<Pair<T,T>, Pair<U,U>> return_fun =
                            (Pair<T, T> curr_pair) -> {
                                T curr_first = curr_pair.getFirst();
                                T curr_second = curr_pair.getSecond();
                                U new_first = fun.apply(curr_first);
                                U new_second = fun.apply(curr_second);
                                Pair<U, U> new_pair = new Pair<>(new_first, new_second);
                                return new_pair;
                            };
                    return return_fun;

                };

        return pairmap_fun;
    }

    private static <T, U> Function<Pair<T,T>, Pair<U,U>> testPairMap (Function<T, U> fun) {

                    Function<Pair<T,T>, Pair<U,U>> return_fun =
                            (Pair<T, T> curr_pair) -> {
                                T curr_first = curr_pair.getFirst();
                                T curr_second = curr_pair.getSecond();
                                U new_first = fun.apply(curr_first);
                                U new_second = fun.apply(curr_second);
                                Pair<U, U> new_pair = new Pair<>(new_first, new_second);
                                return new_pair;
                            };
                    return return_fun;

    }


}
