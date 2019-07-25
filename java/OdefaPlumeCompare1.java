package boomerang.example;

import java.util.function.Function;

import static boomerang.example.TestUtils.*;

public class OdefaPlumeCompare1 {

    public static void main(String... args) {
        boolean t_bool = true;
        boolean f_bool = false;

        MyBoolean t = new MyBoolean(t_bool);
        MyBoolean f = new MyBoolean(f_bool);

        Function<Pair<MyBoolean, MyInteger>, MyBoolean> fun_1 =
                new Function<Pair<MyBoolean, MyInteger>, MyBoolean>() {
                    public MyBoolean apply(Pair<MyBoolean, MyInteger> j_pair) {
                        MyBoolean curr_bool = j_pair.getFirst();
                        MyInteger curr_count_myint = j_pair.getSecond();
                        int curr_count = curr_count_myint.getValue();
                        if (curr_count == 0) {
                            return curr_bool;
                        }
                        {
                            int new_count = curr_count - 1;
                            MyInteger new_count_myint = new MyInteger(new_count);
                            Pair<MyBoolean, MyInteger> new_param = new Pair<MyBoolean, MyInteger>(curr_bool, new_count_myint);
                            MyBoolean new_bool = this.apply(new_param);
                            return new_bool;
                        }
                    }
                };

        int count = 2;
        MyInteger count_myint = new MyInteger(count);

        Pair<MyBoolean, MyInteger> param_1 = new Pair(t_bool, count_myint);
        Pair<MyBoolean, MyInteger> param_2 = new Pair(f_bool, count_myint);

        MyBoolean result_1 = fun_1.apply(param_1);
        MyBoolean result_2 = fun_1.apply(param_2);

        queryFor(result_1);
    }

    private static <T> void queryFor(T query) {
    }
}
