package boomerang.example;

import java.util.function.Function;

import static boomerang.example.TestUtils.*;

public class TestPairMapOneLevelNaive {

    public static void main(String... args) {
        MyInteger one = new MyInteger(1);

        Pair<MyInteger, MyInteger> pair_one = new Pair<>(one, one);

        IncFun inc_fun = new IncFun();
        IntToBoolFun int_bool_fun = new IntToBoolFun();

        Function<Function<MyInteger, Object>, Function<Pair<MyInteger, MyInteger>, Pair<Object, Object>>>
                intobjpairmap = new IntObjPairMap();

        Pair<Object, Object> result_pair_int = intobjpairmap.apply(inc_fun).apply(pair_one);
        Pair<Object, Object> result_pair_bool = intobjpairmap.apply(int_bool_fun).apply(pair_one);

        Object result_pair_int_first = result_pair_int.getFirst();
        Object result_pair_bool_first = result_pair_bool.getFirst();

        System.out.println(result_pair_int_first);
        System.out.println(result_pair_bool_first);

        queryFor(result_pair_int_first);

    }

    private static <T> void queryFor(T query) {

    }

    private static final class IntObjPairMap implements
            Function<Function<MyInteger, Object>, Function<Pair<MyInteger, MyInteger>, Pair<Object, Object>>> {
        private static final class IntObjInnerPairMap implements
                Function<Pair<MyInteger, MyInteger>, Pair<Object, Object>> {
            private final Function<MyInteger, Object> fun;

            private IntObjInnerPairMap(Function<MyInteger, Object> fun) { this.fun = fun; }

            @Override
            public Pair<Object, Object> apply(Pair<MyInteger, MyInteger> curr_pair) {
                MyInteger curr_first = curr_pair.getFirst();
                MyInteger curr_second = curr_pair.getSecond();
                Object new_first = fun.apply(curr_first);
                Object new_second = fun.apply(curr_second);
                Pair<Object, Object> new_pair = new Pair<>(new_first, new_second);
                return new_pair;
            }
        }

        @Override
        public Function<Pair<MyInteger, MyInteger>, Pair<Object, Object>> apply(Function<MyInteger, Object> fun) {
            Function<Pair<MyInteger, MyInteger>, Pair<Object, Object>> return_fun =
                    new IntObjInnerPairMap(fun);
            return return_fun;
        }
    }

    private static class IncFun implements Function<MyInteger, Object> {
        public Object apply(MyInteger curr_myint) {
                MyInteger one = new MyInteger(1);
                return curr_myint.add(one);

        }

    }

    private static class MultFun implements Function<MyInteger, Object> {
        public Object apply(MyInteger curr_myint) {
                MyInteger one = new MyInteger(1);
                return curr_myint.multiply(one);

        }

    }

    private static class IntToBoolFun implements Function<MyInteger, Object> {
        public Object apply(MyInteger curr_myint) {
                MyBoolean tr = new MyBoolean(true);
                int val = curr_myint.getValue();
                if (val > 0) {
                    MyBoolean new_boolean_true = new MyBoolean(true);
                    MyBoolean tr_result = new_boolean_true.and(tr);
                    return tr_result;
                }
                else {
                    MyBoolean new_boolean_false = new MyBoolean(false);
                    MyBoolean fl_result = new_boolean_false.or(tr);
                    return fl_result;
                }
        }
    }


}