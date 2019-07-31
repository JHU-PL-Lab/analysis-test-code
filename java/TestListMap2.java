package boomerang.example;

import java.util.function.Function;
import static boomerang.example.TestUtils.*;

public class TestListMap2 {

    public static void main(String... args) {
        MyBoolean new_bool = new MyBoolean(true);
        LinkedList<MyBoolean> new_list = listMaker(new_bool, 5);

        BoolFlipFun bool_flip_fun = new BoolFlipFun();
        BoolIntFun bool_to_integer_fun = new BoolIntFun();

        LinkedList<Object> result_1 = listMap(bool_flip_fun, new_list);
        LinkedList<Object> result_2 = listMap(bool_to_integer_fun, new_list);

        Object result_1_head = result_1.head;
        Object result_2_head = result_2.head;

        System.out.println(result_1_head);
        System.out.println(result_2_head);

        queryFor(result_1_head);


    }

    private static <T> void queryFor(T query) {

    }

    private static class BoolFlipFun implements Function<Object, Object> {
        public Object apply(Object curr_obj) {
            if (curr_obj instanceof MyBoolean) {
                MyBoolean curr = (MyBoolean) curr_obj;
                boolean val = curr.getValue();
                if (val == false) {
                    MyBoolean new_boolean_true = new MyBoolean(true);
                    return new_boolean_true;
                }
                else {
                    MyBoolean new_boolean_false = new MyBoolean(false);
                    return new_boolean_false;
            }
        }
            else {
                return null;
            }
    }

    }

    private static class BoolIntFun implements Function<Object, Object> {
        public Object apply(Object curr_obj) {
            if (curr_obj instanceof MyBoolean) {
                MyBoolean curr = (MyBoolean) curr_obj;
                boolean val = curr.getValue();
                if (val == true) {
                    MyInteger one = new MyInteger(1);
                    return one;
                } {
                    MyInteger zero = new MyInteger(0);
                    return zero;
                }
            }
            else {
                return null;
            }
        }
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