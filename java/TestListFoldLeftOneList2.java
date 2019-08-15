package boomerang.example;


import java.util.function.Function;

import static boomerang.example.TestUtils.*;

public class TestListFoldLeftOneList2 {

    public static void main(String... args) {
        int list_elm_val = 1;
        IntOption list_elm = new SomeInt(list_elm_val);
        int acc_1_val = 0;
        IntOption acc_1 = new SomeInt(acc_1_val);
        IntOption acc_2 = new NoInt();

        int len = 5;
        LinkedList<IntOption> list_1 = listMaker(list_elm, len);

        Fun1 fun_1 = new Fun1();
        Fun2 fun_2 = new Fun2();

        IntOption lf_result = listFoldLeft(fun_1, acc_1, list_1);

        IntOption lf_result_2 = listFoldLeft(fun_2, acc_2, list_1);

        System.out.println(lf_result);
        System.out.println(lf_result_2);

        queryFor(lf_result_2);

    }

    private static class Fun1 implements Function<Pair<IntOption, IntOption>, IntOption> {
        public IntOption apply (Pair<IntOption, IntOption> j_pair) {
            IntOption j_pair_fst = j_pair.getFirst();
            IntOption j_pair_snd = j_pair.getSecond();
            IntOption add_res = j_pair_fst.add(j_pair_snd);
                return add_res;
        }
    }

    private static class Fun2 implements Function<Pair<IntOption, IntOption>, IntOption> {
        public IntOption apply (Pair<IntOption, IntOption> j_pair) {
            return j_pair.getFirst();
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



