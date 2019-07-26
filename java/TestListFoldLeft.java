package boomerang.example;


import java.util.function.Function;
import static boomerang.example.TestUtils.*;

public class TestListFoldLeft {

    private int fact_result;

    public static void main(String... args) {
        MyInteger new_int = new MyInteger(5);
        MyInteger base_acc = new MyInteger(0);
        LinkedList new_list = listMaker(new_int, 5);

        Function<Pair<MyInteger, MyInteger>, MyInteger> sum_fun =
                ((Pair<MyInteger, MyInteger> j_pair) ->
                {
                    MyInteger first_val = j_pair.getFirst();
                    MyInteger second_val = j_pair.getSecond();
                    MyInteger result = first_val.add(second_val);
                    return result;
                }
                );
        Function<Pair<MyInteger, MyInteger>, MyInteger> product_fun =
                ((Pair<MyInteger, MyInteger> j_pair) ->
                {
                    MyInteger first_val = j_pair.getFirst();
                    MyInteger second_val = j_pair.getSecond();
                    MyInteger result = first_val.multiply(second_val);
                    return result;
                }
                );



//          MyInteger fact_result = factorial (new_int);

        MyInteger lf_result = listFoldLeft(sum_fun, base_acc, new_list);
//        MyInteger fact_result2 = factorial (new_int);

        MyInteger lf_result_2 = listFoldLeft(product_fun, base_acc, new_list);

        queryFor(lf_result);

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
