package boomerang.example;

import java.util.function.Function;

import static boomerang.example.TestUtils.*;

public class TestPairMapTwoLevel {

    public static void main(String... args) {
        MyInteger one = new MyInteger(1);

        Pair<MyInteger, MyInteger> pair_one = new Pair<>(one, one);

        Pair<Pair<MyInteger, MyInteger>, Pair<MyInteger, MyInteger> > pair_two =
                new Pair<>(pair_one, pair_one);

        IncFun inc_fun = new IncFun();
        IntToBoolFun int_bool_fun = new IntToBoolFun();


        Function<Function<? super Object, ?>, Function<Pair<?, ?>, Pair<Object,Object>>>
                pairmap_lv1 = new PairMap<>();

        Function<Function<? super Pair<?, ?>, ? extends Pair<Object, Object>>,
                Function<Pair<? extends Pair<?, ?>, ? extends Pair<?, ?>>, Pair<Pair<Object, Object>,Pair<Object, Object>>>>
                pairmap_lv2 = new PairMap<>();

        Pair<Pair<Object, Object>, Pair<Object, Object>> result_two_pair_int =
                pairmap_lv2.apply(pairmap_lv1.apply(inc_fun)).apply(pair_two);

        Pair<Pair<Object, Object>, Pair<Object, Object>> result_two_pair_bool =
                pairmap_lv2.apply(pairmap_lv1.apply(int_bool_fun)).apply(pair_two);

        Pair<Object, Object> result_two_pair_bool_first = result_two_pair_bool.getFirst();
        Object result_bool = result_two_pair_bool_first.getFirst();

        Pair<Object, Object> result_two_pair_int_first = result_two_pair_int.getFirst();
        Object result_int = result_two_pair_int_first.getFirst();

        System.out.println(result_bool);
        System.out.println(result_int);

        queryFor(result_two_pair_int_first);

    }

    private static <T> void queryFor(T query) {

    }

    private static class IncFun implements Function<Object, Object> {
        public Object apply(Object curr) {
            if (curr instanceof MyInteger) {
                MyInteger curr_myint = (MyInteger) curr;
                MyInteger one = new MyInteger(1);
                return curr_myint.add(one);
            }
            {
                return null;
            }
        }

    }

    private static class MultFun implements Function<Object, Object> {
        public Object apply(Object curr) {
            if (curr instanceof MyInteger ) {
                MyInteger curr_myint = (MyInteger) curr;
                MyInteger one = new MyInteger(1);
                return curr_myint.multiply(one);
            }
            {
                return null;
            }
        }

    }

    private static class IntToBoolFun implements Function<Object, Object> {
        public Object apply(Object curr) {
            if (curr instanceof MyInteger) {
                MyBoolean tr = new MyBoolean(true);
                MyInteger curr_myint = (MyInteger) curr;
                int val = curr_myint.getValue();
                if (val > 0) {
                    MyBoolean new_boolean_true = new MyBoolean(true);
                    MyBoolean tr_result = new_boolean_true.and(tr);
                    return tr_result;
                } {
                    MyBoolean new_boolean_false = new MyBoolean(false);
                    MyBoolean fl_result = new_boolean_false.or(tr);
                    return fl_result;
                }
            }
            {
                return null;
            }
        }
    }

    private static final class PairMap<T, U> implements
            Function<Function<? super T, ? extends U>, Function<Pair<? extends T, ? extends T>, Pair<U, U>>> {
        private static final class InnerPairMap<T, U> implements Function<Pair<? extends T, ? extends T>, Pair<U, U>> {
            private final Function<? super T, ? extends U> fun;

            private InnerPairMap(Function<? super T, ? extends U> fun) {
                this.fun = fun;
            }

            @Override
            public Pair<U, U> apply(Pair<? extends T, ? extends T> curr_pair) {
                T curr_first = curr_pair.getFirst();
                T curr_second = curr_pair.getSecond();
                U new_first = fun.apply(curr_first);
                U new_second = fun.apply(curr_second);
                Pair<U, U> new_pair = new Pair<>(new_first, new_second);
                return new_pair;
            }
        }

        @Override
        public Function<Pair<? extends T, ? extends T>, Pair<U, U>> apply(Function<? super T, ? extends U> fun) {
            Function<Pair<? extends T, ? extends T>, Pair<U, U>> return_fun =
                    new InnerPairMap<T, U>(fun);
            return return_fun;
        }
    }

}
