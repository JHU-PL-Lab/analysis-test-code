package boomerang.example;


import java.util.function.Function;

import static boomerang.example.TestUtils.*;

public class OdefaListFoldLeftOneFun {

    public static void main(String... args) {
        int elm_int_val = 1;
        MyInteger elm_int = new MyInteger(elm_int_val);
        boolean elm_bool_val = true;
        MyBoolean elm_bool = new MyBoolean(elm_bool_val);
        int acc_1_val = 0;
        MyInteger acc_1 = new MyInteger(acc_1_val);
        boolean acc_2_val = false;
        MyBoolean acc_2 = new MyBoolean(acc_2_val);
        int len = 5;
        LinkedList<MyInteger> list_1 = listMaker(elm_int, len);
        LinkedList<MyBoolean> list_2 = listMaker(elm_bool, len);

        // this function assumes that accumulator and int have the
        // same type, and either they are MyInteger or MyBoolean
        Function<Pair<Object, Object>, Object> fun_1 =
                ((Pair<Object, Object> p_pair) ->
                {
                    Object accumulator = p_pair.getFirst();
                    Object item = p_pair.getSecond();
                    if (item instanceof MyInteger)
                    {
                        MyInteger int_item = (MyInteger) item;
                        MyInteger int_accumulator = (MyInteger) accumulator;
                        MyInteger int_result = int_accumulator.add(int_item);
                        return int_result;
                    }
                    else {
                        MyBoolean bool_item = (MyBoolean) item;
                        MyBoolean bool_accumulator = (MyBoolean) accumulator;
                        MyBoolean bool_result = bool_accumulator.or(bool_item);
                        return bool_result;
                    }
                }
                );

        MyInteger lf_result = (MyInteger) listFoldLeft(fun_1, acc_1, list_1);

        MyBoolean lf_result_2 = (MyBoolean) listFoldLeft(fun_1, acc_2, list_2);

        queryFor(lf_result_2);

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
