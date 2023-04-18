package exercise.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;

import java.util.*;

import org.apache.commons.lang3.ArrayUtils;

import exercise.TemplateEngineUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;



public class ArticlesServlet extends HttpServlet {

    private String getId(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            return null;
        }
        String[] pathParts = pathInfo.split("/");
        return ArrayUtils.get(pathParts, 1, null);
    }

    private String getAction(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            return "list";
        }
        String[] pathParts = pathInfo.split("/");
        return ArrayUtils.get(pathParts, 2, getId(request));
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        String action = getAction(request);

        switch (action) {
            case "list":
                showArticles(request, response);
                break;
            default:
                showArticle(request, response);
                break;
        }
    }

    private void showArticles(HttpServletRequest request,
                          HttpServletResponse response)
                throws IOException, ServletException {

        ServletContext context = request.getServletContext();
        Connection connection = (Connection) context.getAttribute("dbConnection");
        // BEGIN
        List<Map<String, String>> articles = new ArrayList<>();

        String query = "SELECT id, title FROM articles ORDER BY id  LIMIT ? OFFSET ?";
        String page = request.getParameter("page");
        int preparePerPage = 10;
        int normalizePage = page == null ? 1 : Integer.parseInt(page);
        int offset = (normalizePage - 1) * preparePerPage;

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, preparePerPage);
            statement.setInt(2, offset);
            ResultSet rs = statement.executeQuery();


            while (rs.next()) {
             articles.add(Map.of("id", rs.getString("id"),
                                    "title", rs.getString("title")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("articles", articles);
        request.setAttribute("page", normalizePage);
        // END
        TemplateEngineUtil.render("articles/index.html", request, response);
    }

    private void showArticle(HttpServletRequest request,
                         HttpServletResponse response)
                 throws IOException, ServletException {

        ServletContext context = request.getServletContext();
        Connection connection = (Connection) context.getAttribute("dbConnection");
        // BEGIN
        String id = getId(request);

        String query = "SELECT * FROM articles WHERE id=?";
        Map<String, String> article = new HashMap<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (!rs.first()) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
            }
                article.put("title", rs.getString("title"));
                article.put("body", rs.getString("body"));


        } catch (SQLException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        request.setAttribute("article", article);
        // END
        TemplateEngineUtil.render("articles/show.html", request, response);
    }
}
