package exercise;

import java.util.Comparator;
import java.util.Map;
import java.util.List;
import java.time.LocalDate;
import java.util.stream.Collectors;

// BEGIN
class Sorter {
    public static List<String> takeOldestMans(List<Map<String, String>> allMans) {
       return allMans.stream()
               .filter(man -> man.get("gender").equals("male"))
               .sorted((m1,m2) -> m1.get("birthday").compareTo(m2.get("birthday")))
               .map(man -> man.get("name"))
               .collect(Collectors.toList());
    }
}
// END
