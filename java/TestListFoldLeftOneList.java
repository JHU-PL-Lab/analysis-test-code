package boomerang.example;


import java.util.function.Function;

import static boomerang.example.TestUtils.*;

public class TestListFoldLeftOneList {

    public static void main(String... args) {
        int list_elm_val = 1;
        int acc_1_val = 0;
        boolean acc_2_val = false;
        MyInteger list_elm = new MyInteger(list_elm_val);
        MyInteger acc_1 = new MyInteger(acc_1_val);
        MyBoolean acc_2 = new MyBoolean(acc_2_val);
        int len = 5;
        LinkedList<MyInteger> list_1 = listMaker(list_elm, len);

        Fun1 fun_1 = new Fun1();
        Fun2 fun_2 = new Fun2();

//          MyInteger fact_result = factorial (new_int);

        Object lf_result = listFoldLeft(fun_1, acc_1, list_1);
//        MyInteger fact_result2 = factorial (new_int);

        Object lf_result_2 = listFoldLeft(fun_2, acc_2, list_1);

        System.out.println(lf_result);
        System.out.println(lf_result_2);

        queryFor(lf_result);

    }

    private static class Fun1 implements Function<Pair<Object, Object>, Object> {
        public Object apply (Pair<Object, Object> j_pair) {
            Object j_pair_fst = j_pair.getFirst();
            Object j_pair_snd = j_pair.getSecond();
            if (j_pair_snd instanceof MyInteger) {
                MyInteger accumulator = (MyInteger) j_pair_fst;
                MyInteger item = (MyInteger) j_pair_snd;
                MyInteger res_match_int = accumulator.add(item);
                return res_match_int;
            }
            else {
                return null;
            }

        }
    }

    private static class Fun2 implements Function<Pair<Object, Object>, Object> {
        public Object apply (Pair<Object, Object> j_pair) {
            Object j_pair_fst = j_pair.getFirst();
            Object j_pair_snd = j_pair.getSecond();
            if (j_pair_snd instanceof MyInteger) {
                MyBoolean accumulator_2 = (MyBoolean) j_pair_fst;
                MyInteger item_2 = (MyInteger) j_pair_fst;
                boolean true_bool = true;
                MyBoolean a_bool = new MyBoolean(true_bool);
                MyBoolean a_combination_of_bool = a_bool.and(accumulator_2);
                return a_combination_of_bool;
            }
            else {
                return null;
            }
        }
    }

    private static <Acc, Elem> Acc listFoldLeft(Function<Pair<Acc, Elem>, Acc> fun, Acc acc, LinkedList<? extends Elem> lst) {
        if (lst == null) {
            return acc;
        } {
            Elem curr_head = lst.head;
            LinkedList<? extends Elem> curr_tail = lst.tail;
            Pair<Acc, Elem> fun_param = new Pair<> (acc, curr_head);

            Acc new_acc = fun.apply(fun_param);

            return listFoldLeft(fun, new_acc, curr_tail);

        }
    }




    private static <T> void queryFor(T query) {

    }



}
