package exercise;


import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.stream.Stream;

// BEGIN
class App{
    public static void main(String[] args) {
        String file = "[program:options]\n" +
                "environment=\"X_FORWARDED_variable=value,  \"\n" +
                "\n" +
                "key=value\n";

        getForwardedVariables(file);
    }

    public static String getForwardedVariables(String file) {
         String [] containFile = file.split("\n");
       return Arrays.stream(containFile)
                .filter(x -> x.startsWith("environment"))
                .map(x -> x.trim().replaceAll("environment=\"", ""))
                .flatMap(x -> Arrays.stream(x.split(",")))
                .filter(x -> x.startsWith("X_FORWARDED_"))
                .map(x -> x.trim().replaceAll("X_FORWARDED_", "")
                        .replaceAll("\"",""))
                .collect(Collectors.joining(","));
    }
}
//END
