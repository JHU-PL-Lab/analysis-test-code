package boomerang.example;

import java.util.function.Function;

import static boomerang.example.TestUtils.*;

public class TestPairMapOneLevel {

    public static void main(String... args) {
        MyInteger one = new MyInteger(1);
        MyBoolean mb_true = new MyBoolean(true);
        MyBoolean mb_false = new MyBoolean(false);

        Pair<MyInteger, MyInteger> pair_one = new Pair<>(one, one);
        Pair<MyBoolean, MyBoolean> pair_one_bool = new Pair<>(mb_true, mb_false);

        Pair<Pair<MyInteger, MyInteger>, Pair<MyInteger, MyInteger> > pair_two =
                new Pair<>(pair_one, pair_one);

//        Pair<Pair<Pair<MyInteger, MyInteger>, Pair<MyInteger, MyInteger>>,
//                Pair<Pair<MyInteger, MyInteger>, Pair<MyInteger, MyInteger> > >
//                pair_three =
//                new Pair<>(pair_two, pair_two);z

        Function<MyInteger, MyInteger> inc_fun =
                ((MyInteger curr_myint) ->
                {
                    int val = curr_myint.getValue();

                    return curr_myint.add(one);
                }
                );


        Function<MyInteger, MyBoolean> int_to_bool_conversion_fun =
                ((MyInteger curr_myint) ->
                {
                    int val = curr_myint.getValue();
                    if (val > 0) {
                        MyBoolean new_boolean_true = new MyBoolean(true);
                        return new_boolean_true;
                    } {
                    MyBoolean new_boolean_false = new MyBoolean(false);
                    return new_boolean_false;
                }

                }
                );

        Function<MyBoolean, MyBoolean> flip_fun =
                ((MyBoolean curr_mybool) ->
                {
                    boolean val = curr_mybool.getValue();
                    if (val == true) {
                        return new MyBoolean(false);
                    } {
                        return new MyBoolean(true);
                }
                }
                );


        Function<Function<MyInteger, MyInteger>, Function<Pair<MyInteger, MyInteger>, Pair<MyInteger, MyInteger>>>
                pmlv1_int_to_int = pairMap();
        Pair<MyInteger, MyInteger> result_pair_int = pmlv1_int_to_int.apply(inc_fun).apply(pair_one);

        Function<Function<MyInteger, MyBoolean>, Function<Pair<MyInteger, MyInteger>, Pair<MyBoolean, MyBoolean>>>
                pmlv1_int_to_bool = pairMap();
        Pair<MyBoolean, MyBoolean> result_pair_bool = pmlv1_int_to_bool.apply(int_to_bool_conversion_fun).apply(pair_one);

//        Function<Function<MyBoolean, MyBoolean>, Function<Pair<MyBoolean, MyBoolean>, Pair<MyBoolean, MyBoolean>>>
//                pmlv1_bool_to_bool = pairMap();
//        Pair<MyBoolean, MyBoolean> result_pair_bool = pmlv1_bool_to_bool.apply(flip_fun).apply(pair_one_bool);


        MyInteger result_pair_int_first = result_pair_int.getFirst();
        MyBoolean result_pair_bool_first = result_pair_bool.getFirst();


        queryFor(result_pair_int_first);

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