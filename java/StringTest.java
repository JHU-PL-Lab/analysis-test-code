package boomerang.example;

import java.util.function.Function;

public class StringTest {

    public static void main(String[] args) {
        String test = new String("new");
        String test2 = test;

        queryFor(test2);
    }

    private static <T> void queryFor(T test2) {
    }


}