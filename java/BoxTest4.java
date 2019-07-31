package boomerang.example;

import java.util.function.Function;

public class BoxTest4 {

    public static void main(String[] args) {

        Integer one = new Integer(1);
        Integer two = new Integer(2);

        Box<Integer> box_one = new Box<Integer>(one){};
        Box<Integer> box_two = new Box<Integer>(two){};

        boxId boxId = new boxId();

        Box<Integer> box_one_p = boxId.apply(box_one);
        Box<Integer> box_two_p = boxId.apply(box_two);

        System.out.println(box_two_p);

        queryFor(box_one_p.getValue());
    }

    private static class boxId {

        public boxId() { }

        public Box<Integer> apply(Box<Integer> curr_box) {
            Integer box_val = curr_box.getValue();
            Box<Integer> new_box = new Box<>(box_val);
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