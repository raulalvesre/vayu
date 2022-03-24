package br.com.vayu.servlet;

import br.com.vayu.dao.CategoryDAO;
import br.com.vayu.models.Category;
import br.com.vayu.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@WebServlet("/listaCategorias")
public class CategoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) {

        System.out.println(request.getContextPath());
        try {
            Path htmlTemplatePath = getHtmlTemplatePath();
            String htmlTemplateContent = Files.readString(htmlTemplatePath);

            EntityManager em = JPAUtil.getEntityManager();
            CategoryDAO categoryDAO = new CategoryDAO(em);

            List<Category> categories = categoryDAO.findAll();

            StringBuilder newHtmlBodyContent = new StringBuilder("<h1>Categorias</h1>");

            for (Category ct : categories) {
                newHtmlBodyContent.append("""
                        <h2 style="background-color:%s; max-width: 200px">%s</h2>
                        <img src="%s" style="max-height: 300px"><br/>
                        <p>%s</p>
                        <p>%s</p>""".formatted(ct.getColorCode(),
                        ct.getName(),
                        ct.getIconPath(),
                        ct.getDescription(),
                        ct.getStudyGuide()));
            }

            String newHtmlContent = htmlTemplateContent.replace("$body", newHtmlBodyContent.toString());
            newHtmlContent = newHtmlContent.replace("null", "");

            PrintWriter out = response.getWriter();
            out.println(newHtmlContent);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Path getHtmlTemplatePath() throws URISyntaxException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL htmlTemplateURL = classLoader.getResource("template.html");
        return Path.of(htmlTemplateURL.toURI());
    }

}
