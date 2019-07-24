package boomerang.example;

import java.util.function.Function;

public class BoxTest4 {

    public static void main(String[] args) {

        Box<Integer> box_one = new Box<>(new Integer(1));
        Box<Integer> box_two = new Box<>(new Integer(2));

        boxId boxId = new boxId();

        Box<Integer> box_one_p = boxId.apply(box_one);
        Box<Integer> box_two_p = boxId.apply(box_two);

//        Unit<Integer> res = box_one_p;
//        System.out.println();

//        queryFor(box_one_p);
    }

    private static class boxId implements Function<Box<Integer>, Box<Integer>> {

        public boxId() { }

        public Box<Integer> apply(Box<Integer> curr_box) {
            Integer box_val = curr_box.getValue();
            Box<Integer> new_box = new Box<>(new Integer(0));
            queryFor(new_box);
            return new_box;
        }}


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


}