package exercise.servlet;

import exercise.Data;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import static exercise.Data.getCompanies;


public class CompaniesServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {


        PrintWriter out = response.getWriter();
        String queryStr = request.getQueryString();

        if (queryStr != null ) {
            String requestValue = request.getParameter("search");
            List<String> validCompany = getValidCompany(getCompanies(), requestValue);

            if (validCompany.isEmpty()) {
                out.println("Companies not found");
            } else {
                for (String s : validCompany){
                    out.println(s);
                }
        }

        }
        if (queryStr == null) {
            for (String company : getCompanies()) {
                out.println(company);
            }
        }

            }

    public List<String> getValidCompany(List<String> companies, String param) {
        return companies.stream()
                .filter(p -> p.contains(param))
                .collect(Collectors.toList());
    }
}

