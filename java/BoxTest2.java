package boomerang.example;

import java.util.function.Function;


// This test takes the box out of consideration; now it's just adding two integers

public class BoxTest2 {

    public static void main(String[] args) {
        MyInteger one = new MyInteger(1);
        MyInteger two = new MyInteger(2);

        BoxInc boxInc1 = new BoxInc();

        MyInteger result_box_int = boxInc1.apply(one);
        MyInteger result_box_int_2 = boxInc1.apply(two);

//        int result_pair_int_first = result_box_int.getValue();
        System.out.println(result_box_int.getValue());


        queryFor(result_box_int);
    }


    public static class MyInteger {
        private int value;

        public MyInteger(int value) {
            this.value = value;
        }

        public int getValue() {

            int return_value = this.value;
            return return_value;
        }

        // method that lets you add the MyInteger with another MyInteger, and returns the result as a new MyInteger.
        public MyInteger add(MyInteger int2) {
            int val1 = this.getValue();
            int val2 = int2.getValue();
            int result_val = val1 + val2;
            MyInteger result_integer = new MyInteger(result_val);
            return result_integer;
        }

    }

//    public static class Unit<T> {
//        private T value;
//
//        public Unit(T value) {
//            this.value = value;
//        }
//
//        public T getValue() {
//
//            T return_this = this.value;
//            return return_this;
//        }
//
//    }

    private static <T> void queryFor(T query) {

    }


    private static class BoxInc implements Function<MyInteger, MyInteger> {

        public BoxInc() {
        }

        public MyInteger apply(MyInteger curr_int) {
            MyInteger one_to_add = new MyInteger(1);
            MyInteger new_int = curr_int.add(one_to_add);
            return new_int;
        }

        ;
    }


}