package boomerang.example;

import java.util.function.Function;

public class BoxTest1 {

    public static void main(String[] args) {
        MyInteger one = new MyInteger(1);
        MyInteger two = new MyInteger(2);


        Box<MyInteger> box_one = new Box<>(one);
        Box<MyInteger> box_two = new Box<>(two);

        BoxInc boxInc1 = new BoxInc();
//        BoxInc boxInc2 = new BoxInc();


        Box<MyInteger> result_box_int = boxInc1.apply(box_one);
        Box<MyInteger> result_box_int_2 = boxInc1.apply(box_two);

        MyInteger result_pair_int_first = result_box_int.getValue();
//        System.out.println(result_pair_int_first.getValue());


        queryFor(result_pair_int_first);


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

    public static class Box<T> {
        private T value;

        public Box(T value) {
            this.value = value;
        }

        public T getValue() {

            T return_this = this.value;
            return return_this;
        }

    }

    private static <T> void queryFor(T query) {

    }


    private static class BoxInc implements Function<Box<MyInteger>, Box<MyInteger>> {

        public BoxInc() {
        }

        public Box<MyInteger> apply(Box<MyInteger> curr_box) {
            MyInteger curr_first = curr_box.getValue();
            MyInteger one_to_add = new MyInteger(1);
            MyInteger new_first = curr_first.add(one_to_add);
            Box<MyInteger> new_box = new Box<>(new_first);
            return new_box;
        }

        ;
    }


}