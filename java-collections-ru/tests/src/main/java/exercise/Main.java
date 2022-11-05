package exercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> result = new ArrayList<>(Arrays.asList(1, 2, 3));
        System.out.println(App.take(result, 5));
    }
}
