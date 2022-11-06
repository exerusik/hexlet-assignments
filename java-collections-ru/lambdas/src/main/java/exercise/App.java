package exercise;

import java.util.Arrays;

// BEGIN
class App {
    public static String[][] enlargeArrayImage(String[][] array) {
      String[][] full = Arrays.stream(array)
                .map(element -> Arrays.stream(element)
                    .flatMap(item -> Arrays.stream(new String[]{item, item}))
                    .toArray(String[]::new))
                .flatMap(item -> Arrays.stream(new String[][]{item, item}))
                .toArray(String[][]::new);

        return full;
    }

    public static void main(String[] args) {
        String[][] image = {
                {"*", "*", "*", "*"},
                {"*", " ", " ", "*"},
                {"*", " ", " ", "*"},
                {"*", "*", "*", "*"},
        };
        String[][] enlargedImage = App.enlargeArrayImage(image);
        System.out.println(Arrays.deepToString(enlargedImage));
    }
}
// END
