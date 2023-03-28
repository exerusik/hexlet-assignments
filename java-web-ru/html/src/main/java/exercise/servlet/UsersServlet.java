package exercise.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import exercise.User;
import org.apache.commons.lang3.ArrayUtils;

public class UsersServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        String pathInfo = request.getPathInfo();

        if (pathInfo == null) {
            showUsers(request, response);
            return;
        }

        String[] pathParts = pathInfo.split("/");
        String id = ArrayUtils.get(pathParts, 1, "");

        showUser(request, response, id);
    }

    private List getUsers() throws JsonProcessingException, IOException {
        // BEGIN
        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(new File("src/main/resources/users.json"), new TypeReference<List<User>>() {
        });
        // END
    }

    private void showUsers(HttpServletRequest request,
                          HttpServletResponse response)
                throws IOException {

        PrintWriter printWriter = response.getWriter();
        List<User> userList = getUsers();
        StringBuilder body = new StringBuilder();

        body.append("""
                <!DOCTYPE html>
                <html lang=\"ru\">
                    <head>
                        <meta charset=\"UTF-8\">
                        <title>Example application | Users</title>
                        <link rel=\"stylesheet\" href=\"mysite.css\">
                    </head>
                    <body>
                    <table>
                    <tr>
                    
                """);
        // BEGIN
        for (User user : userList){
                body.append( "<td>" + user.id + "</td>" +
                            "<td>" +
                            "<a href=" + "/users/" + user.id + ">" + user.firstName + " " + user.lastName + "</a>" +
                            "</td>");
                        }

                        body.append("""
                    </body>
                </html>
                """);

        printWriter.println(body.toString());


        // END
    }

    private void showUser(HttpServletRequest request,
                         HttpServletResponse response,
                         String id)
                 throws IOException {

        // BEGIN
        List<User> users= getUsers();
        List<String> existId = new ArrayList<>();
        for (User user : users){
            existId.add(user.id);

        }
        if (!existId.contains(id)) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Not Found");
        }
        PrintWriter out = response.getWriter();

        for (User user : users){
            out.println(user.firstName + " " + user.lastName + "\n");
            out.println("id " + user.id);
            out.println("email " + user.email);
        }
        // END
    }
}
