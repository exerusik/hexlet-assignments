package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
class App {
    public static void main(String[] args) {
        Map words = getWordCount("java is the best programming language java");
        System.out.println(toString(words));
    }
    public static Map getWordCount(String sentence) {

        HashMap<String, Integer> discription = new HashMap<>();
        if (sentence.isEmpty()) {
            return discription;
        }
        String[] words = sentence.split(" ");
        int countWorld;

        for (int i = 0; i < words.length; i++) {
            countWorld = 0;
            for (String word : words) {
                if (words[i].equals(word)) {
                    countWorld++;
                }
            }
            discription.put(words[i], countWorld);
        }
        return discription;
    }
    public static String toString(Map<String, Integer> discription) {
        if (discription.isEmpty()) {
            return "{}";
        }
        StringBuilder result = new StringBuilder("{\n");
        for (Map.Entry<String, Integer> words : discription.entrySet()) {
            result.append("  ")
                    .append(words.getKey())
                    .append(": ")
                    .append(words.getValue())
                    .append("\n");
        }
        result.append("}");
        return result.toString();
    }
}
//END
