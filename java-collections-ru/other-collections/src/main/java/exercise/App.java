package exercise;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// BEGIN
class App {
    public static void main(String[] args) {
        Map<String, Object> data1 = new HashMap<>(
                Map.of("one", "eon", "two", "two", "four", true, "abs", 'h')
        );
        Map<String, Object> data2 = new HashMap<>(
                Map.of("two", "own", "zero", 4, "four", true)
        );

        App.genDiff(data1, data2);
    }

    public static LinkedHashMap<String, String> genDiff(Map<String, Object> dictionary, Map<String, Object> dictionary1) {
            /*
            "added" — ключ отсутствовал в первом массиве, но был добавлен во второй
            "deleted" — ключ был в первом массиве, но отсутствует во втором
            ****"changed" — ключ присутствовал и в первом и во втором массиве, но значения отличаются
            ****"unchanged" — ключ присутствовал и в первом и во втором массиве с одинаковыми значениями
             */

        Set<String> keys = new TreeSet<>(dictionary.keySet());
        keys.addAll(dictionary1.keySet());
        LinkedHashMap<String, String> result = new LinkedHashMap<>();

        for (String key : keys) {
            if (!dictionary.containsKey(key)) {
                result.put(key, "added");
            } else if (!dictionary1.containsKey(key)) {
                result.put(key, "deleted");
            }else if (dictionary.get(key).equals(dictionary1.get(key))) {
                result.put(key, "unchanged");
            } else {
                result.put(key, "changed");
            }
        }
        return result;
    }
}
//END
