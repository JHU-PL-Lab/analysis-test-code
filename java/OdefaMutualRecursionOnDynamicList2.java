package boomerang.example;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static boomerang.example.TestUtils.*;


public class OdefaMutualRecursionOnDynamicList2 {

    public static void main(String... args) {
        int zero = 0;
        int three_val = 0;
        MyInteger three = new MyInteger(three_val);

        boolean t_val = true;
        MyBoolean t = new MyBoolean(t_val);

        boolean f_val = false;
        MyBoolean f = new MyBoolean(f_val);

        Function<MyInteger, LinkedList<MyBoolean>> list_maker =
                new Function<MyInteger, LinkedList<MyBoolean>>() {
                    public LinkedList<MyBoolean> apply(MyInteger length) {
                        int length_int = length.getValue();
                        boolean false_bool = false;
                        MyBoolean false_mybool = new MyBoolean(false_bool);
                        boolean true_bool = true;
                        MyBoolean true_mybool = new MyBoolean(true_bool);

                        if (length_int == 0) {
                            return null;
                        }
                        {
                            int rest_length_int = length_int - 1;
                            MyInteger rest_length = new MyInteger(rest_length_int);
                            LinkedList<MyBoolean> rest = this.apply(rest_length);
                            LinkedList<MyBoolean> new_list_second_elm = new LinkedList<>();
                            new_list_second_elm.head = false_mybool;
                            new_list_second_elm.tail = rest;
                            LinkedList<MyBoolean> new_list = new LinkedList<>();
                            new_list.head = true_mybool;
                            new_list.tail = new_list_second_elm;
                            return new_list;
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

        int len_div_2_int = 5;
        MyInteger len_div_2 = new MyInteger(len_div_2_int);
        LinkedList<MyBoolean> list_1 = list_maker.apply(len_div_2);

        Function<LinkedList<MyBoolean>, MyBoolean> fun_f = f_list.get(zero);

        MyBoolean result = fun_f.apply(list_1);

        queryFor(result);

    }

    private static <T> void queryFor(T query) {

    }
}
