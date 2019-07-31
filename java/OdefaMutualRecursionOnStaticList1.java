package boomerang.example;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static boomerang.example.TestUtils.LinkedList;
import static boomerang.example.TestUtils.MyBoolean;

public class OdefaMutualRecursionOnStaticList1 {

    public static void main(String... args) {

        boolean t_val = true;
        MyBoolean t = new MyBoolean(t_val){};

        boolean f_val = false;
        MyBoolean f = new MyBoolean(f_val){};

        // statically create a list with alternating boolean values
        LinkedList<MyBoolean> tail_rec = null;

        LinkedList<MyBoolean> list_create_1 = new LinkedList<>();
        list_create_1.head = f;
        list_create_1.tail = tail_rec;

        LinkedList<MyBoolean> list_create_2 = new LinkedList<>();
        list_create_2.head = t;
        list_create_2.tail = list_create_1;

        LinkedList<MyBoolean> list_create_3 = new LinkedList<>();
        list_create_3.head = f;
        list_create_3.tail = list_create_2;

        LinkedList<MyBoolean> list_create_4 = new LinkedList<>();
        list_create_4.head = t;
        list_create_4.tail = list_create_3;

        LinkedList<MyBoolean> list_create_5 = new LinkedList<>();
        list_create_5.head = f;
        list_create_5.tail = list_create_4;

        LinkedList<MyBoolean> list_create_6 = new LinkedList<>();
        list_create_6.head = t;
        list_create_6.tail = list_create_5;

        LinkedList<MyBoolean> list_create_7 = new LinkedList<>();
        list_create_7.head = f;
        list_create_7.tail = list_create_6;

        LinkedList<MyBoolean> list_create_8 = new LinkedList<>();
        list_create_8.head = t;
        list_create_8.tail = list_create_7;

        LinkedList<MyBoolean> list_create_9 = new LinkedList<>();
        list_create_9.head = f;
        list_create_9.tail = list_create_8;

        LinkedList<MyBoolean> list_create_10 = new LinkedList<>();
        list_create_10.head = t;
        list_create_10.tail = list_create_9;

        LinkedList<MyBoolean> alt_list_1 = list_create_10;

        // initializing mutually recursive routines
        F f_fun = new F();
        G g_fun = new G();

        f_fun.setG(g_fun);
        g_fun.setF(f_fun);

        MyBoolean res = f_fun.apply(alt_list_1);

        System.out.println(res.getValue());

        queryFor(res);

    }

    private static <T> void queryFor(T query) {

    }

    private static class F implements Function<LinkedList<MyBoolean>, MyBoolean> {
        G g_fun;

        public void setG (G g_fun) { this.g_fun = g_fun; }

        public MyBoolean apply (LinkedList<MyBoolean> alt_list) {
            LinkedList<MyBoolean> alt_list_tail = alt_list.tail;
            if (alt_list_tail == null) {
                boolean f_t_val = true;
                MyBoolean f_t = new MyBoolean(f_t_val);
                return f_t;
            }
            else {
                MyBoolean alt_list_head = alt_list.head;
                MyBoolean rest2 = g_fun.apply(alt_list_tail);
                MyBoolean f_and = alt_list_head.and(rest2);
                return f_and;
            }
        }
    }

    private static class G implements Function<LinkedList<MyBoolean>, MyBoolean> {
        F f_fun;

        public void setF (F f_fun) { this.f_fun = f_fun; }

        public MyBoolean apply (LinkedList<MyBoolean> alt_list) {
            LinkedList<MyBoolean> alt_list_tail = alt_list.tail;
            if (alt_list_tail == null) {
                boolean g_t_val = true;
                MyBoolean g_t = new MyBoolean(g_t_val);
                return g_t;
            }
            else {
                MyBoolean alt_list_2_head = alt_list.head;
                MyBoolean rest3 = f_fun.apply(alt_list_tail);
                MyBoolean head_not = alt_list_2_head.not();
                MyBoolean g_and = head_not.and(rest3);
                return g_and;
            }
        }
    }
}
