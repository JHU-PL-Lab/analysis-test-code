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

        IncFun inc_fun = new IncFun();

        Function<Function<? super Object, ?>, Function<Pair<?, ?>, Pair<Object,Object>>>
                pairmap_lv1 = new PairMap<>();

        Function<Function<? super Pair<?, ?>, ? extends Pair<Object, Object>>,
                Function<Pair<? extends Pair<?, ?>, ? extends Pair<?, ?>>, Pair<Pair<Object, Object>,Pair<Object, Object>>>>
                pairmap_lv2 = new PairMap<>();

        Pair<Pair<Object, Object>, Pair<Object, Object>> call =
                pairmap_lv2.apply(pairmap_lv1.apply(inc_fun)).apply(pair_two);

        Pair<Object, Object> intermediate_res = call.getFirst();

        Object res = intermediate_res.getSecond();

        System.out.println(res);

        queryFor(res);

    }

    private static <T> void queryFor(T query) {

    }

    private static class IncFun implements Function<Object, Object> {
        public Object apply(Object curr) {
            if (curr instanceof MyInteger) {
                MyInteger curr_myint = (MyInteger) curr;
                int val = curr_myint.getValue();
                int one_val = 1;
                MyInteger one = new MyInteger(one_val);
                return curr_myint.add(one);
            }
            else {
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
