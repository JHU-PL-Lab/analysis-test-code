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

        SumFun sum_fun = new SumFun();
        AndFun and_fun = new AndFun();

        Object lf_result = listFoldLeft(sum_fun, base_acc_int, new_int_list);

        Object lf_result_2 = listFoldLeft(and_fun, base_acc_bool, new_bool_list);

        System.out.println(lf_result);
        System.out.println(lf_result_2);

//        queryFor(lf_result);
        queryFor(lf_result);

    }


    private static class SumFun implements Function<Pair<Object, Object>, Object> {
        public Object apply(Pair<Object, Object> curr) {
            Object curr_first = curr.getFirst();
            Object curr_second = curr.getSecond();
                MyInteger curr_acc = (MyInteger) curr_first;
                MyInteger curr_myint = (MyInteger) curr_second;
                return curr_acc.add(curr_myint);
        }

    }

    private static class AndFun implements Function<Pair<Object, Object>, Object> {
        public Object apply(Pair<Object, Object> curr) {
            Object curr_first = curr.getFirst();
            Object curr_second = curr.getSecond();
                MyBoolean curr_acc = (MyBoolean) curr_first;
                MyBoolean curr_mybool = (MyBoolean) curr_second;
                return curr_acc.and(curr_mybool);
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
