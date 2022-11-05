package exercise;


import java.util.List;
// BEGIN
class App {
    public static long getCountOfFreeEmails(List<String> emails) {
        long count =  emails.stream()
               .filter(email -> email.endsWith("gmail.com"))
               .count();
        long count1 = emails.stream()
                    .filter(email -> email.endsWith("yandex.ru"))
                       .count();
        long count2 = emails.stream()
                    .filter(email -> email.endsWith("hotmail.com"))
                    .count();
        return count + count1 + count2;
    }
}
// END
