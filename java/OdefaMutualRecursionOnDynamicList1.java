package boomerang.example;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static boomerang.example.TestUtils.*;


public class OdefaMutualRecursionOnDynamicList1 {

    public static void main(String... args) {
        int zero = 0;
        int three_val = 0;
        MyInteger three = new MyInteger(three_val);

        boolean t_val = true;
        MyBoolean t = new MyBoolean(t_val);

        boolean f_val = false;
        MyBoolean f = new MyBoolean(f_val);

        Function<Pair<MyBoolean, MyInteger>, LinkedList<MyBoolean>> altListMaker =
                new Function<Pair<MyBoolean, MyInteger>, LinkedList<MyBoolean>>() {
                    public LinkedList<MyBoolean> apply(Pair<MyBoolean, MyInteger> j_pair) {
                        MyBoolean bool_to_add = j_pair.getFirst();
                        MyInteger curr_count_myint = j_pair.getSecond();
                        int curr_count = curr_count_myint.getValue();
                        if (curr_count == 0) {
                            return null;
                        }
                        {
                            int new_count = curr_count - 1;
                            MyInteger new_count_myint = new MyInteger(new_count);
                            MyBoolean next_bool_to_add = bool_to_add.not();
                            Pair<MyBoolean, MyInteger> next_pair = new Pair<>(next_bool_to_add, new_count_myint);
                            LinkedList<MyBoolean> rest1 = this.apply(next_pair);
                            LinkedList<MyBoolean> result = new LinkedList<>();
                            result.head = bool_to_add;
                            result.tail = rest1;
                            return result;
                        }

                    }
                };

        // mutual recursion routine
        List<Function<LinkedList<MyBoolean>, MyBoolean>> f_list = new ArrayList<>();
        List<Function<LinkedList<MyBoolean>, MyBoolean>> g_list = new ArrayList<>();

        Function<LinkedList<MyBoolean>, MyBoolean> new_f =
                ((LinkedList<MyBoolean> alt_list) -> {
                    LinkedList<MyBoolean> alt_list_tail = alt_list.tail;
                    if (alt_list_tail == null) {
                        boolean f_t_val = true;
                        MyBoolean f_t = new MyBoolean(f_t_val);
                        return f_t;
                    }
                    {
                        MyBoolean alt_list_head = alt_list.head;
                        Function<LinkedList<MyBoolean>, MyBoolean> g_fun = g_list.get(zero);
                        MyBoolean rest2 = g_fun.apply(alt_list_tail);
                        MyBoolean f_and = alt_list_head.and(rest2);
                        return f_and;
                    }
                }
                );

        Function<LinkedList<MyBoolean>, MyBoolean> new_g =
                ((LinkedList<MyBoolean> alt_list) -> {
                    LinkedList<MyBoolean> alt_list_tail = alt_list.tail;
                    if (alt_list_tail == null) {
                        boolean g_t_val = true;
                        MyBoolean g_t = new MyBoolean(g_t_val);
                        return g_t;
                    }
                    {
                        MyBoolean alt_list_2_head = alt_list.head;
                        Function<LinkedList<MyBoolean>, MyBoolean> f_fun = f_list.get(zero);
                        MyBoolean rest3 = f_fun.apply(alt_list_tail);
                        MyBoolean head_not = alt_list_2_head.not();
                        MyBoolean g_and = head_not.and(rest3);
                        return g_and;
                    }
                }
                );


        f_list.add(new_f);
        g_list.add(new_g);

        Pair<MyBoolean, MyInteger> list_create_param = new Pair<>(t, three);
        LinkedList<MyBoolean> alt_list_1 = altListMaker.apply(list_create_param);

        Function<LinkedList<MyBoolean>, MyBoolean> fun_f = f_list.get(zero);

        MyBoolean res_1 = fun_f.apply(alt_list_1);

        queryFor(res_1);

    }

    private static <T> void queryFor(T query) {

    }
}
