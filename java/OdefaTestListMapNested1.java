package boomerang.example;

import java.util.function.Function;

import static boomerang.example.TestUtils.*;

public class OdefaTestListMapNested1 {

    public static void main(String... args) {
        int one_val = 1;
        int three_val = 3;
        MyInteger one = new MyInteger(one_val);
        LinkedList<MyInteger> inner_list = listMaker(one, three_val);
        LinkedList<LinkedList<MyInteger>> outer_list = listMaker(inner_list, three_val);

        LinkedList<LinkedList<MyInteger>> dummy = null;

        IncFun inc_fun = new IncFun();

        Function<Function<? super MyInteger, ? extends MyInteger>,
                Function<LinkedList<? extends MyInteger>, LinkedList<MyInteger>>> listMap_lv1 = new ListMap<MyInteger, MyInteger>();

        Function<Function<? super LinkedList<MyInteger>, ? extends LinkedList<MyInteger>>,
                Function<LinkedList<? extends LinkedList<MyInteger>>, LinkedList<LinkedList<MyInteger>>>> listMap_lv2 =
                new ListMap<LinkedList<MyInteger>, LinkedList<MyInteger>>();

        LinkedList<LinkedList<MyInteger>> res_nested_list =
                listMap_lv2.apply(listMap_lv1.apply(inc_fun)).apply(outer_list);

        Object res_inner_list = res_nested_list.head;
//
//        LinkedList<MyInteger> res_inner_list = res_nested_list.head;
//
//        Object res = res_inner_list.head;

        queryFor(res_inner_list);
    }

    private static <T> void queryFor(T query) {

    }

    private static class IncFun implements Function<MyInteger, MyInteger> {
        public MyInteger apply(MyInteger curr) {
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

    private static final class ListMap<T, U> implements
            Function<Function<? super T, ? extends U>,
                    Function<LinkedList<? extends T>, LinkedList<U>>> {
                private static final class InnerListMap<T,U> implements Function<LinkedList<? extends T>, LinkedList<U>> {
                    private final Function<? super T, ? extends U> fun;

                    private InnerListMap(Function<? super T, ? extends U> fun) { this.fun = fun; }

                    @Override
                    public LinkedList<U> apply(LinkedList<? extends T> lst) {
                        if (lst == null) {
                            LinkedList<U> null_res = null;
                            return null_res;
                        } {
                            T curr_head = lst.head;
                            U new_head = fun.apply(curr_head);
                            LinkedList<? extends T> curr_tail = lst.tail;
                            ListMap new_list_map = new ListMap();
                            Function<LinkedList<? extends T>, LinkedList<U>> curry = new_list_map.apply(fun);
                            LinkedList<U> new_tail = curry.apply(curr_tail);

                            LinkedList<U> result_list = new LinkedList();

                            result_list.head = new_head;
                            result_list.tail = new_tail;
                            return result_list;

                        }
                    }
                }

                public Function<LinkedList<? extends T>, LinkedList<U>> apply (Function<? super T, ? extends U> fun) {
                    Function<LinkedList<? extends T>, LinkedList<U>> return_fun =
                            new InnerListMap<T, U>(fun);
                    return return_fun;
                }
    }

}