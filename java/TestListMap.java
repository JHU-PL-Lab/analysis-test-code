package boomerang.example;

import java.util.function.Function;
import static boomerang.example.TestUtils.*;

public class TestListMap {

    public static void main(String... args) {
        MyInteger new_int = new MyInteger(5);
        LinkedList<MyInteger> int_list_1 = myIntListMaker(5);

        Function<MyInteger, MyInteger> inc_fun =
                ((MyInteger curr_myint) -> {
                    MyInteger one = new MyInteger(1);
                    return curr_myint.add(one);
                }
                );

        Function<MyInteger, MyBoolean> int_to_bool_conversion_fun =
                ((MyInteger curr_myint) -> {
                    int val = curr_myint.getValue();
                    if (val > 0) {
                        MyBoolean new_boolean_true = new MyBoolean(true);
                        return new_boolean_true;
                } {
                    MyBoolean new_boolean_false = new MyBoolean(false);
                    return new_boolean_false;
                }
                }
                );

        LinkedList<MyInteger> list_1 = listMap(inc_fun, int_list_1);
        MyInteger list_1_head = list_1.head;

        LinkedList<MyBoolean> list_2 = listMap(int_to_bool_conversion_fun, int_list_1);
        MyBoolean list_2_head = list_2.head;
    }

    private static <T> void queryFor(T query) {

    }


    private static <T, U> LinkedList<U> listMap (Function<T, U> fun, LinkedList<T> lst) {
        if (lst == null) {
            return null;
        } {
            T curr_head = lst.head;
            U new_head = fun.apply(curr_head);
            LinkedList<T> curr_tail = lst.tail;
            LinkedList<U> new_tail = listMap(fun, curr_tail);

            LinkedList<U> result_list = new LinkedList();

            result_list.head = new_head;
            result_list.tail = new_tail;
            return result_list;

        }
    }
}