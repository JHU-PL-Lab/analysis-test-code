package boomerang.example;

import java.util.function.Function;
import java.util.function.Predicate;

import static boomerang.example.TestUtils.*;

public class TestListFilter {


    public static void main(String... args) {

        //statically construct list [5; false]
        int new_int_val = 5;
        boolean new_bool_val = false;
        Object new_int = new MyInteger(new_int_val);
        Object new_bool = new MyBoolean(new_bool_val);

        LinkedList<Object> dummy_list_0 = null;
        LinkedList<Object> dummy_list_1 = new LinkedList<>();
        dummy_list_1.head = new_bool;
        dummy_list_1.tail = dummy_list_0;

        LinkedList<Object> dummy_list_2 = new LinkedList<>();
        dummy_list_2.head = new_int;
        dummy_list_2.tail = dummy_list_1;

        LinkedList<Object> test_list = dummy_list_2;

        LinkedList<Object> res_list = listFilter(is_mybool, test_list);

        Object res_list_head = res_list.head;

        System.out.println(res_list_head);

        queryFor(res_list_head);

    }

        public static Predicate<Object> is_mybool = new Predicate<Object>() {
            @Override
            public boolean test(Object o) {
                boolean res = (o instanceof MyBoolean);
                System.out.println(res);
                return res;
            }
        };


    private static <Elem> LinkedList<Elem> listFilter(Predicate<Elem> pred, LinkedList<Elem> lst) {
        if (lst == null) {
            return lst;
        }
        else {
            Elem curr_head = lst.head;
            LinkedList<Elem> curr_tail = lst.tail;
            boolean meetsPredicate = pred.test(curr_head);
            LinkedList<Elem> rest = listFilter(pred, curr_tail);
            if (meetsPredicate) {
                LinkedList<Elem> new_list = new LinkedList<>();
                new_list.head = curr_head;
                new_list.tail = rest;
                return new_list;
            }
            else {
                return rest;
            }
        }

    }

    private static <T> void queryFor(T query) {

    }

}
