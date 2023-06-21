package exercise;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// BEGIN
@RestController
class WelcomeController {
    @GetMapping("/")
    public String greetingSpring() {
        return "Welcome to Spring";
    }
    @GetMapping("/hello")
    public String greetingUser(@RequestParam(required = false) String name) {
        return name == null ? "Hello, World!" : "Hello, " + name + "!";
    }

}
// END
