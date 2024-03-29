package exercise;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import io.javalin.Javalin;
import io.ebean.DB;

import exercise.domain.User;
import exercise.domain.query.QUser;
import io.ebean.Database;

class AppTest {

    private static Javalin app;
    private static String baseUrl;

    // BEGIN
    @BeforeAll
    static void beforeAll() {
        app = App.getApp();
        app.start();
        baseUrl = "http://localhost:" + app.port();
    }
    // END

    // Между тестами база данных очищается
    // Благодаря этому тесты не влияют друг на друга
    @BeforeEach
    void beforeEach() {
        Database db = DB.getDefault();
        db.truncate("user");
        User existingUser = new User("Wendell", "Legros", "a@a.com", "123456");
        existingUser.save();
    }

    @Test
    void testUsers() {

        // Выполняем GET запрос на адрес http://localhost:port/users
        HttpResponse<String> response = Unirest
            .get(baseUrl + "/users")
            .asString();
        // Получаем тело ответа
        String content = response.getBody();

        // Проверяем код ответа
        assertThat(response.getStatus()).isEqualTo(200);
        // Проверяем, что страница содержит определенный текст
        assertThat(response.getBody()).contains("Wendell Legros");
    }

    @Test
    void testNewUser() {

        HttpResponse<String> response = Unirest
            .get(baseUrl + "/users/new")
            .asString();

        assertThat(response.getStatus()).isEqualTo(200);
    }

    // BEGIN

    @Test
    void createValidUser() {
        String firstName = "Ruslan";
        String lastName = "Alekseev";
        String email = "exe.rusik@gmail.com";
        String password = "1998";
        HttpResponse response = Unirest
                .post(baseUrl + "/users")
                .field("firstName", firstName)
                .field("lastName", lastName)
                .field("email", email)
                .field("password", password)
                .asEmpty();

        assertThat(response.getStatus()).isEqualTo(302);

        User user = new QUser()
                .lastName.equalTo(lastName)
                .findOne();

        assertThat(user).isNotNull();
        assertThat(user.getFirstName()).isEqualTo(firstName);
        assertThat(user.getLastName()).isEqualTo(lastName);
        assertThat(user.getEmail()).isEqualTo(email);

    }

    @Test
    void invalidUser() {
        String firstName = "";
        String lastName = "";
        String email = "exe.rusik.com";
        String password = "0";

        HttpResponse<String> response = Unirest
                .post(baseUrl + "/users")
                .field("firstName", firstName)
                .field("lastName", lastName)
                .field("email", email)
                .field("password", password)
                .asString();

        assertThat(response.getStatus()).isEqualTo(422);

        User invalidUser = new QUser()
                .lastName.equalTo(lastName).findOne();

        assertThat(invalidUser).isNull();

        String context = response.getBody();
        assertThat(context).contains("Имя не должно быть пустым");
        assertThat(context).contains("Фамилия не должна быть пустой");
        assertThat(context).contains("Должно быть валидным email");
        assertThat(context).contains("Пароль должен содержать не менее 4 символов");


    }
    @AfterAll
   static void afterAll(){
        app.stop();
    }
    // END
}
