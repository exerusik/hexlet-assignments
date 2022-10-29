package exercise;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

// BEGIN
class App {
    public static void main(String[] args) {
        List<Map> books = new ArrayList<>();

        Map<String, String> book1 = new HashMap<>(
                Map.of("title", "Cymbeline", "author", "Shakespeare", "year", "1611")
        );
        Map<String, String> book2 = new HashMap<>(
                Map.of("title", "Book of Fooos", "author", "FooBar", "year", "1111")
        );
        Map<String, String> book3 = new HashMap<>(
                Map.of("title", "The Tempest", "author", "Shakespeare", "year", "1611")
        );
        Map<String, String> book4 = new HashMap<>(
                Map.of("title", "Book of Foos Barrrs", "author", "FooBar", "year", "2222")
        );
        Map<String, String> book5 = new HashMap<>(
                Map.of("title", "Still foooing", "author", "FooBar", "year", "3333")
        );

        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);
        books.add(book5);

        Map<String, String> where = new HashMap<>(Map.of("author", "Shakespeare", "year", "1611"));

       // List<Map> result = App.findWhere(books, where);

        //System.out.println(result); // =>
// [
//     {title=Cymbeline, year=1611, author=Shakespeare},
//     {title=The Tempest, year=1611, author=Shakespeare}
    }
    public static List findWhere(List<Map<String, String>> books, Map<String, String> where) {
        List<Map> result = new ArrayList<>();
        for (Map<String, String> book : books) {
            boolean isCheck = true;
            for (Map.Entry properties : where.entrySet()) {
                isCheck = true;
                if (book.containsKey(properties.getKey()) && book.containsValue(properties.getValue())) {
                    isCheck = false;
                } else {
                    break;
                }
            }
            if (!isCheck) {
                result.add(book);
            }
        }
        return result;
    }
}
//END
