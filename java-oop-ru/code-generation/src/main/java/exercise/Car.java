package exercise;

import lombok.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;


// BEGIN
@Getter
@Setter
@Value
// END
class Car {
    int id;
    String brand;
    String model;
    String color;
    User owner;

    // BEGIN
    public String serialize() throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    return mapper.writeValueAsString(this);

    }

    public static Car unserialize(Path pathToFile) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Car s = mapper.readValue(new File(pathToFile.toUri()), Car.class);
        return s;
    }
    // END
}

