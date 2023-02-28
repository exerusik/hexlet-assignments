package exercise;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
// BEGIN
class App{
    public static void save(Path path, Car car) throws IOException {
        String result = car.serialize();
        Files.writeString(path, result, StandardOpenOption.WRITE);

    }

    public static Car extract(Path path) throws IOException {
    return Car.unserialize(path);
    }
}
// END
