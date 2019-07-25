package boomerang.example;

import java.util.function.Function;

import static boomerang.example.TestUtils.*;

public class OdefaTwoLevelPairmapInt {

    public static void main(String... args) {
        int p0_val = 0;
        int p1_val = 1;

        MyInteger p0 = new MyInteger(p0_val);
        MyInteger p1 = new MyInteger(p1_val);

        Pair<MyInteger, MyInteger> pair_one = new Pair<>(p0, p1);

        Pair<Pair<MyInteger, MyInteger>, Pair<MyInteger, MyInteger> > pair_two =
                new Pair<>(pair_one, pair_one);

        Function<MyInteger, MyInteger> inc_fun =
                ((MyInteger curr_myint) ->
                {
                    int val = curr_myint.getValue();
                    int one_val = 1;
                    MyInteger one = new MyInteger(one_val);
                    return curr_myint.add(one);
                }
                );

        Function<Function<MyInteger, MyInteger>, Function<Pair<MyInteger, MyInteger>, Pair<MyInteger, MyInteger>>>
                pmlv1_int_to_int = pairMap();

        Function<Function<Pair<MyInteger, MyInteger>, Pair<MyInteger, MyInteger>>,
                Function<Pair<Pair<MyInteger, MyInteger>, Pair<MyInteger, MyInteger>>,
                        Pair<Pair<MyInteger, MyInteger>, Pair<MyInteger, MyInteger>>>> pmlv2_int_to_int = pairMap();

        Function<Pair<MyInteger, MyInteger>, Pair<MyInteger, MyInteger>> one_pmap =
                pmlv1_int_to_int.apply(inc_fun);

        Function<Pair<Pair<MyInteger, MyInteger>, Pair<MyInteger, MyInteger>>,
                Pair<Pair<MyInteger, MyInteger>, Pair<MyInteger, MyInteger>>> two_pmap =
                pmlv2_int_to_int.apply(one_pmap);

        Pair<Pair<MyInteger, MyInteger>, Pair<MyInteger, MyInteger>> call =
                two_pmap.apply(pair_two);

        Pair<MyInteger, MyInteger> intermediate_res = call.getFirst();

        MyInteger res = intermediate_res.getSecond();

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
