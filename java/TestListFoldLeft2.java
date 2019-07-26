package boomerang.example;

import java.util.function.Function;
import static boomerang.example.TestUtils.*;

// This test confirms that conflation does NOT occur when there are two iterations of List.fold_left,
// one with integer list/acc (fun int -> int), and one with boolean list/acc (fun bool -> bool)

public class TestListFoldLeft2 {


    private int fact_result;

    public static void main(String... args) {
        MyInteger new_int = new MyInteger(5);
        MyInteger base_acc_int = new MyInteger(0);
        LinkedList<MyInteger> new_int_list = listMaker(new_int, 5);

        MyBoolean new_bool = new MyBoolean(true);
        MyBoolean base_acc_bool = new MyBoolean(false);
        LinkedList<MyBoolean> new_bool_list = listMaker(new_bool, 5);

        Function<Pair<MyInteger, MyInteger>, MyInteger> sum_fun =
                ((Pair<MyInteger, MyInteger> j_pair) ->
                {
                    MyInteger first_val = j_pair.getFirst();
                    MyInteger second_val = j_pair.getSecond();
                    return first_val.add(second_val);
                }
                );
        Function<Pair<MyInteger, MyInteger>, MyInteger> product_fun =
                ((Pair<MyInteger, MyInteger> j_pair) ->
                {
                    MyInteger first_val = j_pair.getFirst();
                    MyInteger second_val = j_pair.getSecond();
                    return first_val.multiply(second_val);
                }
                );

        Function<Pair<MyBoolean, MyBoolean>, MyBoolean> and_fun =
                ((Pair<MyBoolean, MyBoolean> j_pair) ->
                {
                    MyBoolean first_val = j_pair.getFirst();
                    MyBoolean second_val = j_pair.getSecond();
                    return first_val.and(second_val);
                }
                );



//          MyInteger fact_result = factorial (new_int);

        MyInteger lf_result = listFoldLeft(sum_fun, base_acc_int, new_int_list);
//        MyInteger fact_result2 = factorial (new_int);

        MyBoolean lf_result_2 = listFoldLeft(and_fun, base_acc_bool, new_bool_list);

//        queryFor(lf_result);
        queryFor(lf_result_2);

    }

//    public static MyInteger factorial (MyInteger num) {
//        int curr_val = num.getValue();
//        if (curr_val == 1) {
//            return num;
//        } {
//            MyInteger prev = factorial(new MyInteger(curr_val - 1));
//            return num.multiply(prev);
//        }
//
//
//    }


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
