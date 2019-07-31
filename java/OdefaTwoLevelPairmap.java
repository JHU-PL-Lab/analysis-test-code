package boomerang.example;

import java.util.function.Function;

import static boomerang.example.TestUtils.*;

public class OdefaTwoLevelPairmap {

    public static void main(String... args) {
        boolean p0_val = true;
        int p1_val = 1;

        MyBoolean p0 = new MyBoolean(p0_val);
        MyInteger p1 = new MyInteger(p1_val);
        Pair<MyBoolean, MyInteger> pair_one = new Pair<MyBoolean, MyInteger>(p0, p1);
        Pair<Pair<MyBoolean, MyInteger>, Pair<MyBoolean, MyInteger>> pair_two =
                new Pair<Pair<MyBoolean, MyInteger>, Pair<MyBoolean, MyInteger>> (pair_one, pair_one);

        Ident identFunction = new Ident();

        Function<Function<? super Object, ?>, Function<Pair<?, ?>, Pair<Object,Object>>>
                identityMapper_lv1 = new PairMap<>();

        Function<Function<? super Pair<?, ?>, ? extends Pair<Object, Object>>,
                 Function<Pair<? extends Pair<?, ?>, ? extends Pair<?, ?>>, Pair<Pair<Object, Object>,Pair<Object, Object>>>>
                identityMapper_lv2 = new PairMap<>();

        Pair<Pair<Object, Object>, Pair<Object, Object>> call =
                identityMapper_lv2.apply(identityMapper_lv1.apply(identFunction)).apply(pair_two);

        Pair<Object, Object> intermediate_res = call.getFirst();

        Object res = intermediate_res.getSecond();

        queryFor(res);

    }

    private static <T> void queryFor(T query) {
    }

    private static class Ident implements Function<Object, Object> {
        public Object apply(Object curr) {
            return curr;
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
