package boomerang.example;


import java.util.function.Function;

import static boomerang.example.TestUtils.*;

public class OdefaListFoldLeftOneFun2 {

    public static void main(String... args) {
        int elm_int_val = 1;
        SomeInt elm_int = new SomeInt(elm_int_val);
        NoInt elm_noint = new NoInt();
        int acc_1_val = 0;
        SomeInt acc_1 = new SomeInt(acc_1_val);
        int acc_2_val = 100;
        SomeInt acc_2 = new SomeInt(acc_2_val);
        int len = 5;
        LinkedList<IntOption> list_1 = listMaker(elm_int, len);
        LinkedList<IntOption> list_2 = listMaker(elm_noint, len);

        Fun1 fun_1 = new Fun1();

        IntOption lf_result = listFoldLeft(fun_1, acc_1, list_1);

        IntOption lf_result_2 = listFoldLeft(fun_1, acc_2, list_2);
        System.out.println(lf_result);
        System.out.println(lf_result_2);

        queryFor(lf_result);

    }

    private static <T> void queryFor(T query) {

    }


    // this function assumes that accumulator and int have the
    // same type, and either they are MyInteger or MyBoolean
    private static class Fun1 implements Function<Pair<IntOption, IntOption>, IntOption> {
        public IntOption apply(Pair<IntOption, IntOption> p_pair) {
            IntOption accumulator = p_pair.getFirst();
            IntOption curr = p_pair.getSecond();
            return accumulator.add(curr);
        }
    }

    private static <Acc, Elem> Acc listFoldLeft(Function<Pair<Acc, Elem>, Acc> fun, Acc acc, LinkedList<Elem> lst) {
        if (lst == null) {
            return acc;
        } {
            Elem curr_head = lst.head;
            LinkedList<Elem> curr_tail = lst.tail;
            Pair<Acc, Elem> fun_param = new Pair<> (acc, curr_head);

            Acc new_acc = fun.apply(fun_param);

            return listFoldLeft(fun, new_acc, curr_tail);

        }
    }


}
