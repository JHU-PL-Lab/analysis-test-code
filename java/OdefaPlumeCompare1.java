package boomerang.example;

import java.util.function.Function;

import static boomerang.example.TestUtils.*;

public class OdefaPlumeCompare1 {

    public static void main(String... args) {
        boolean t_bool = true;
        boolean f_bool = false;

        MyBoolean t = new MyBoolean(t_bool);
        MyBoolean f = new MyBoolean(f_bool);

        int count = 2;
        MyInteger count_myint = new MyInteger(count);

        Pair<MyBoolean, MyInteger> param_1 = new Pair<>(t, count_myint);
        Pair<MyBoolean, MyInteger> param_2 = new Pair<>(f, count_myint);

        Object result_1 = fun1Wrapper(param_1);
        Object result_2 = fun1Wrapper(param_2);

        System.out.println(result_1);
        System.out.println(result_2);

        queryFor(f);
    }

    private static <T> void queryFor(T query) {
    }

    private static class Fun_1 implements Function<Pair<Object, Object>, Object> {
        public Object apply(Pair<Object, Object> j_pair){
            Object curr_first = j_pair.getFirst();
            Object curr_second = j_pair.getSecond();
            if (curr_second instanceof MyInteger) {
                MyInteger curr_count_myint = (MyInteger) curr_second;
                int curr_count = curr_count_myint.getValue();
                if (curr_count == 0) {
                    return curr_first;
                }
                else {
                    MyBoolean curr_bool = (MyBoolean) curr_first;
                    int new_count = curr_count - 1;
                    MyInteger new_count_myint = new MyInteger(new_count);
                    Pair<Object, Object> new_param = new Pair<Object, Object>(curr_bool, new_count_myint);
                    Object new_bool = apply(new_param);
                    return new_bool;
                }
            }
            else {
                return null;
            }

        }
    }

    private static Object fun1Wrapper (Pair<? extends Object, ? extends Object> param) {
        Object curr_first = param.getFirst();
        Object curr_second = param.getSecond();
        if (curr_second instanceof MyInteger) {
            if (curr_first instanceof MyBoolean) {
                Object new_first = curr_first;
                Object new_second = curr_second;
                Pair<Object, Object> new_param = new Pair<>(new_first, new_second);
                Fun_1 fun_1 = new Fun_1();
                Object res = fun_1.apply(new_param);
                return res;
            }
            else {
                return null;
            }
        }
        else {
            return null;
        }
    }
}
