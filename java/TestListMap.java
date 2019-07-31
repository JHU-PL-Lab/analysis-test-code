package boomerang.example;

import java.util.function.Function;

import static boomerang.example.TestUtils.*;

public class TestListMap {

    public static void main(String... args) {
        MyInteger new_int = new MyInteger(5);
        LinkedList<MyInteger> int_list_1 = myIntListMaker(5);

//        IncFun inc_fun = new IncFun();
        IntToBoolFun int_to_bool_conversion_fun = new IntToBoolFun();
        MultFun mult_fun = new MultFun();

        LinkedList<Object> list_1 = listMap(mult_fun, int_list_1);
        Object list_1_head = list_1.head;

        LinkedList<Object> list_2 = listMap(int_to_bool_conversion_fun, int_list_1);

        Object list_2_head = list_2.head;

        System.out.println(list_1_head);
        System.out.println(list_2_head);

        queryFor(list_1_head);
    }

    private static <T> void queryFor(T query) {

    }

    private static class IncFun implements Function<Object, Object> {
        public Object apply(Object curr) {
            if (curr instanceof MyInteger) {
                MyInteger curr_myint = (MyInteger) curr;
                MyInteger one = new MyInteger(1);
                return curr_myint.add(one);
            }
            else {
                return null;
            }
        }

    }

    private static class MultFun implements Function<Object, Object> {
        public Object apply(Object curr) {
            if (curr instanceof MyInteger ) {
                MyInteger curr_myint = (MyInteger) curr;
                MyInteger one = new MyInteger(1);
                return curr_myint.multiply(one);
            }
            else {
                return null;
            }
        }

    }

    private static class IntToBoolFun implements Function<Object, Object> {
        public Object apply(Object curr) {
            if (curr instanceof MyInteger) {
                MyBoolean tr = new MyBoolean(true);
                MyInteger curr_myint = (MyInteger) curr;
                int val = curr_myint.getValue();
                if (val > 0) {
                    MyBoolean new_boolean_true = new MyBoolean(true);
                    MyBoolean tr_result = new_boolean_true.and(tr);
                    return tr_result;
                }
                else {
                    MyBoolean new_boolean_false = new MyBoolean(false);
                    MyBoolean fl_result = new_boolean_false.or(tr);
                    return fl_result;
                }
            }
            {
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