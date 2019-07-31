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

        IncFun inc_fun = new IncFun();

        Function<Function<? super Object, ?>, Function<Pair<?, ?>, Pair<Object,Object>>>
                pairmap_lv1 = new PairMap<>();

        Function<Function<? super Pair<?, ?>, ? extends Pair<Object, Object>>,
                Function<Pair<? extends Pair<?, ?>, ? extends Pair<?, ?>>, Pair<Pair<Object, Object>,Pair<Object, Object>>>>
                pairmap_lv2 = new PairMap<>();

        Pair<Pair<Object, Object>, Pair<Object, Object>> res_nested_pair =
                pairmap_lv2.apply(pairmap_lv1.apply(inc_fun)).apply(pair_two);

        Pair<Object, Object> res_pair = res_nested_pair.getFirst();

        Object res = res_pair.getFirst();

        queryFor(res);

    }

    private static <T> void queryFor(T query) {

    }

    private static class IncFun implements Function<Object, Object> {
        public Object apply(Object n) {
            if (n instanceof MyBoolean) {
                MyBoolean n_mybool = (MyBoolean) n;
                boolean val = n_mybool.getValue();
                if (val == true) {
                    boolean new_false_val = false;
                    MyBoolean new_false = new MyBoolean(new_false_val);
                    return new_false;
                }
                else {
                    boolean new_true_val = true;
                    MyBoolean new_true = new MyBoolean(new_true_val);
                    return new_true;
                }
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
