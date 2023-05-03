package exercise.controllers;

import io.javalin.http.Context;
import io.javalin.apibuilder.CrudHandler;
import io.ebean.DB;

import java.util.ArrayList;
import java.util.List;

import exercise.domain.User;
import exercise.domain.query.QUser;

import org.apache.commons.lang3.Validate;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.lang3.StringUtils;

public class UserController implements CrudHandler {

    public void getAll(Context ctx) {
        // BEGIN
        List<User> users = new QUser()
                .orderBy()
                .id.asc()
                .findList();

        String json = DB.json().toJson(users);

        ctx.json(json);
        // END
    };

    public void getOne(Context ctx, String id) {

        // BEGIN
        User user = new QUser()
                .id.equalTo(Integer.parseInt(id))
                .findOne();

        String json = DB.json().toJson(user);

        ctx.json(json);
        // END
    };

    public void create(Context ctx) {

        // BEGIN
        User user = ctx.bodyValidator(User.class)
                .check(p -> !p.getFirstName().isEmpty(), "firstname is not empty")
                .check(p -> !p.getLastName().isEmpty(), "lastname is not empty")
                .check(p -> EmailValidator.getInstance().isValid(p.getEmail()), "email is not valid")
                .check(p -> StringUtils.isNumeric(p.getPassword()), "password should be only numbers")
                .check(p -> p.getPassword().length() >= 4, "password should be more 4 digit")
                .get();
        user.save();
        // END
    };

    public void update(Context ctx, String id) {
        // BEGIN
     String body = ctx.body();
     User updateUser = DB.json().toBean(User.class, body);
     updateUser.setId(id);
     updateUser.update();
        // END
    };

    public void delete(Context ctx, String id) {
        // BEGIN
        new QUser().id.equalTo(Long.parseLong(id)).delete();
        // END
    };
}
