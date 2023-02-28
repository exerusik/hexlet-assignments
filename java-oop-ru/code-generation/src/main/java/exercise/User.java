package exercise;

import lombok.Getter;
import lombok.Value;

// BEGIN
@Getter
@Value
// END
class User {
    int id;
    String firstName;
    String lastName;
    int age;
}
