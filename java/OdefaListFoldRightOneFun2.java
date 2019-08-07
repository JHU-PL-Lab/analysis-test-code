package boomerang.example;


import java.util.function.Function;

import static boomerang.example.TestUtils.*;

public class OdefaListFoldRightOneFun2 {

    public static void main(String... args) {
        int elm_int_val = 1;
        IntOption elm_int = new SomeInt(elm_int_val);
        IntOption elm_no_int = new NoInt();
        int acc_1_val = 0;
        IntOption acc_1 = new SomeInt(acc_1_val);
        int acc_2_val = 1;
        IntOption acc_2 = new SomeInt(acc_2_val);
        int len = 5;

        // List of no ints
        LinkedList<IntOption> list_1 = listMaker(elm_no_int, len);

        // List of integers
        LinkedList<IntOption> list_2 = listMaker(elm_int, len);

        Fun1 fun_1 = new Fun1();

        IntOption lf_result = listFoldRight(fun_1, acc_1, list_1);

        IntOption lf_result_2 = listFoldRight(fun_1, acc_2, list_2);

        System.out.println("lf_result: " + lf_result);
        System.out.println("lf_result_2: " + lf_result_2);

        queryFor(lf_result);

    }

    private static class Fun1 implements Function<Pair<IntOption, IntOption>, IntOption> {
        public IntOption apply (Pair<IntOption, IntOption> p_pair) {
            IntOption accumulator = p_pair.getFirst();
            IntOption item = p_pair.getSecond();
            return accumulator.add(item);
        }
    }

    private static <Acc, Elem> Acc listFoldRight(Function<Pair<Acc, Elem>, Acc> fun, Acc acc, LinkedList<? extends Elem> lst) {
        if (lst == null) {
            return acc;
        }
        else {
            Elem curr_head = lst.head;
            LinkedList<? extends Elem> curr_tail = lst.tail;

            Acc new_acc = listFoldRight(fun, acc, curr_tail);
            Pair<Acc, Elem> fun_param = new Pair<>(new_acc, curr_head);

            Acc result = fun.apply(fun_param);
            return result;

        }
    }

    private static class Error {

    }

    private static <T> void queryFor(T query) {

    }

}

