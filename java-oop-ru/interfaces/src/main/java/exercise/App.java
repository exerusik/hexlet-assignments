package exercise;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
class App{
    public static List<String>  buildApartmentsList(List<Home> allBuild, int count) {
        return
     allBuild.stream()
             .sorted(Home::compareTo)
             .limit(count)
             .map(Home::toString)
             .toList();
    }
}
// END
