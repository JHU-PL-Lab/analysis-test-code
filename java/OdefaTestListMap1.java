package boomerang.example;

import java.util.function.Function;
import static boomerang.example.TestUtils.*;

public class OdefaTestListMap1 {

    public static void main(String... args) {
        int int_one = 1;
        MyInteger myint_one = new MyInteger(int_one);
        int three = 3;
        LinkedList<MyInteger> list1_step2 = listMaker(myint_one, three);

        boolean bool_true = true;
        MyBoolean mybool_true = new MyBoolean(bool_true);
        LinkedList<MyBoolean> list2_step2 = listMaker(mybool_true, three);

        // The inc function; if input is MyInteger, increases it by one. If it's a MyBoolean,
        // gets the negation of that boolean.
        Function<Object, Object> inc =
                ((Object elm) -> {
                    if (elm instanceof MyInteger) {
                        MyInteger int_elm = (MyInteger) elm;
                        int one_val = 1;
                        MyInteger one = new MyInteger(one_val);
                        MyInteger int_result = int_elm.add(one);
                        return int_result;
                    }
                    {
                        MyBoolean bool_elm = (MyBoolean) elm;
                        MyBoolean bool_result = bool_elm.not();
                        return bool_result;
                    }
                }
                );

        LinkedList<Object> list_1 = listMap(inc, list1_step2);
        // static access
//        MyInteger list_1_head = (MyInteger) list_1.head;
        // dynamic access
        MyInteger list_1_head = (MyInteger) listAccess(list_1, int_one);

        LinkedList<Object> list_2 = listMap(inc, list2_step2);
        // static access
//        MyBoolean list_2_head = (MyBoolean) list_2.head;
        // dynamic access
        MyBoolean list_2_head = (MyBoolean) listAccess(list_2, int_one);

        queryFor(list_2_head);
    }

    private static <T> void queryFor(T query) {

    }


    private static <T, U> LinkedList<U> listMap (Function<T, U> fun, LinkedList<? extends T> lst) {
        if (lst == null) {
            return null;
        } {
            T curr_head = lst.head;
            U new_head = fun.apply(curr_head);
            LinkedList<? extends T> curr_tail = lst.tail;
            LinkedList<U> new_tail = listMap(fun, curr_tail);

            LinkedList<U> result_list = new LinkedList();

            result_list.head = new_head;
            result_list.tail = new_tail;
            return result_list;

        }
    }
}
