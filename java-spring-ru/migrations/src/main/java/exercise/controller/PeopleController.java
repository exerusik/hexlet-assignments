package exercise.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/people")
@RequiredArgsConstructor
public class PeopleController {


    private final JdbcTemplate jdbc;


    @PostMapping(path = "")
    public void createPerson(@RequestBody Map<String, Object> person) {
        String query = "INSERT INTO person (first_name, last_name) VALUES (?, ?)";
        jdbc.update(query, person.get("first_name"), person.get("last_name"));
    }

    // BEGIN
    @GetMapping(path = "")
    public List<Map<String, Object>> showPersons() {
        String query = "SELECT * FROM person";
      return jdbc.queryForList(query);
    }
    @GetMapping(path = "/{id}")
    public List<Map<String, Object>> showPerson(@PathVariable long id) {
        String query = "SELECT first_name, last_name FROM person WHERE id=" + id;
        return jdbc.queryForList(query);
    }
    // END
}
