package boomerang.example;

import java.util.function.Function;

public class UnitTest {

    public static void main(String[] args) {
        Rock rock_1 = new Rock(){};
        Rock rock_2 = new Rock(){};

        RockId rockId = new RockId();

        Rock new_rock_1 = rockId.apply(rock_1);
        Rock new_rock_2 = rockId.apply(rock_2);

//        queryFor(new_rock_1);
    }

    private static class RockId implements Function<Rock, Rock> {
        public RockId() { }

        public Rock apply(Rock curr_box) {
            queryFor(curr_box);
            Rock rock = new Rock();
            return rock;
        }
    }


    public static class Rock {
    }

    private static <T> void queryFor(T query) {

    }


}