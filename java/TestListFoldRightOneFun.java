package boomerang.example;


import java.util.function.Function;

import static boomerang.example.TestUtils.*;

public class SPDSTestListFoldRightOneFun {

    public static void main(String... args) {
        int list_elm_val = 1;
        int acc_1_val = 0;
        int acc_2_val = 100;
        MyInteger list_elm = new MyInteger(list_elm_val);
        MyInteger acc_1 = new MyInteger(acc_1_val);
        MyInteger acc_2 = new MyInteger(acc_2_val);
        int len = 5;
        LinkedList<MyInteger> list_1 = listMaker(list_elm, len);
        LinkedList<MyInteger> list_2 = myIntListMaker(len);

        Function<Pair<MyInteger, MyInteger>, MyInteger> sum_fun =
                ((Pair<MyInteger, MyInteger> j_pair) ->
                {
                    MyInteger first_val = j_pair.getFirst();
                    MyInteger second_val = j_pair.getSecond();
                    return first_val.add(second_val);
                }
                );


//          MyInteger fact_result = factorial (new_int);

        MyInteger lf_result = listFoldRight(sum_fun, acc_1, list_1);
//        MyInteger fact_result2 = factorial (new_int);

        MyInteger lf_result_2 = listFoldRight(sum_fun, acc_2, list_1);

        queryFor(acc_2);

    }

    private static <Acc, Elem> Acc listFoldRight(Function<Pair<Acc, Elem>, Acc> fun, Acc acc, LinkedList<Elem> lst) {
        if (lst == null) {
            return acc;
        } {
            Elem curr_head = lst.head;
            LinkedList<Elem> curr_tail = lst.tail;

            Acc new_acc = listFoldRight(fun, acc, curr_tail);
            Pair<Acc, Elem> fun_param = new Pair<>(new_acc, curr_head);

            Acc result = fun.apply(fun_param);
            return result;

        }
    }



    private static <T> void queryFor(T query) {

    }



}
