package boomerang.example;


import java.util.function.Function;

import static boomerang.example.TestUtils.*;

public class SPDSTestListFoldLeftOneList {

    public static void main(String... args) {
        int list_elm_val = 1;
        int acc_1_val = 0;
        boolean acc_2_val = false;
        MyInteger list_elm = new MyInteger(list_elm_val);
        MyInteger acc_1 = new MyInteger(acc_1_val);
        MyBoolean acc_2 = new MyBoolean(acc_2_val);
        int len = 5;
        LinkedList<MyInteger> list_1 = listMaker(list_elm, len);


        Function<Pair<MyInteger, MyInteger>, MyInteger> fun_1 =
                ((Pair<MyInteger, MyInteger> j_pair) ->
                {
                    MyInteger accumulator = j_pair.getFirst();
                    MyInteger item = j_pair.getSecond();
                    MyInteger res_match_int = accumulator.add(item);
                    return res_match_int;
                }
                );
        Function<Pair<MyBoolean, MyInteger>, MyBoolean> fun_2 =
                ((Pair<MyBoolean, MyInteger> j_pair) ->
                {
                    MyBoolean accumulator_2 = j_pair.getFirst();
                    MyInteger item_2 = j_pair.getSecond();
                    boolean true_bool = true;
                    MyBoolean a_bool = new MyBoolean(true_bool);
                    MyBoolean a_combination_of_bool = a_bool.and(accumulator_2);
                    return a_combination_of_bool;
                }
                );



//          MyInteger fact_result = factorial (new_int);

        MyInteger lf_result = listFoldLeft(fun_1, acc_1, list_1);
//        MyInteger fact_result2 = factorial (new_int);

        MyBoolean lf_result_2 = listFoldLeft(fun_2, acc_2, list_1);

        queryFor(lf_result_2);

    }




    private static <Acc, Elem> Acc listFoldLeft(Function<Pair<Acc, Elem>, Acc> fun, Acc acc, LinkedList<Elem> lst) {
        if (lst == null) {
            return acc;
        } {
            Elem curr_head = lst.head;
            LinkedList<Elem> curr_tail = lst.tail;
            Pair<Acc, Elem> fun_param = new Pair<> (acc, curr_head);

            // want to apply the function here... syntax?
            Acc new_acc = fun.apply(fun_param);

            return listFoldLeft(fun, new_acc, curr_tail);

        }
    }



    private static <T> void queryFor(T query) {

    }



}
