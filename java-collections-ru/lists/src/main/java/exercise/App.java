package exercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// BEGIN
class App {
    public static void main(String[] args) {

        System.out.println(scrabble("rkqodlw", "woRld"));
    }

    public static boolean scrabble(String digits, String word) {
        String[] everyDigit = digits.toLowerCase()
                                    .split("");
        List <String> sameDigits = new ArrayList<>(Arrays.asList(everyDigit));
        String[] digitsOfWord = word.toLowerCase()
                                    .split("");
        List<String> everyDigitOfWord = new ArrayList<>(Arrays.asList(digitsOfWord));
        List<String> check = new ArrayList<>();

        for (String digit : everyDigitOfWord) {
            for (String digitOfSameDigits : sameDigits) {
                if (digit.equals(digitOfSameDigits)) {
                    check.add(digitOfSameDigits);
                    sameDigits.remove(digitOfSameDigits);
                    break;
                }
            }
        }
        return everyDigitOfWord.equals(check);
    }
}
//END
