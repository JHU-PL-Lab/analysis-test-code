package boomerang.example;

import java.util.function.Function;

import static boomerang.example.TestUtils.*;

public class TestListFoldRightOneList2 {

    public static void main (String... args) {
        IntOption list_elm = new SomeInt(1);

        IntOption acc_1 = new SomeInt(0);
        IntOption acc_2 = new NoInt();
        int len = 5;

        LinkedList<IntOption> list_1 = listMaker(list_elm, len);

        Fun1 fun_1 = new Fun1();
        Fun2 fun_2 = new Fun2();

//          MyInteger fact_result = factorial (new_int);

        IntOption lf_result = listFoldRight(fun_1, acc_1, list_1);
//        MyInteger fact_result2 = factorial (new_int);

        IntOption lf_result_2 = listFoldRight(fun_2, acc_2, list_1);

        System.out.println("lf_result: " + lf_result);
        System.out.println("lf_result_2: " + lf_result_2);

        queryFor(lf_result_2);

    }

    private static <T> void queryFor(T query) {

    }

    private static class Fun1 implements Function<Pair<IntOption, IntOption>, IntOption> {
        public IntOption apply (Pair<IntOption, IntOption> p_pair) {
//            queryFor(p_pair.getFirst());
            IntOption accumulator = p_pair.getFirst();
            IntOption item = p_pair.getSecond();
            IntOption res = accumulator.add(item);
//            queryFor(res);
            return res;
        }
    }

    private static class Fun2 implements Function<Pair<IntOption, IntOption>, IntOption> {
        public IntOption apply(Pair<IntOption, IntOption> j_pair) {
//            queryFor(res);
            return j_pair.getFirst();
        }
    }


    private static <Acc, Elem> Acc listFoldRight(Function<Pair<Acc, Elem>, Acc> fun, Acc acc, LinkedList<Elem> lst) {
        if (lst == null) {
            return acc;
        }
        else {
            Elem curr_head = lst.head;
            LinkedList<Elem> curr_tail = lst.tail;

            Acc new_acc = listFoldRight(fun, acc, curr_tail);
            Pair<Acc, Elem> fun_param = new Pair<>(new_acc, curr_head);

            Acc result = fun.apply(fun_param);
            return result;

        }
    }

}
