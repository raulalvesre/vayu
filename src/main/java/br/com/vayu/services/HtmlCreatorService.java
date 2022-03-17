package br.com.vayu.services;

import br.com.vayu.dao.CourseDAO;
import br.com.vayu.dto.CourseHtmlDTO;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class HtmlCreatorService {

    public static void generateCoursesHtml() {
        List<CourseHtmlDTO> courses = CourseDAO.findAllByVisibleTrueAsCourseHtmlDTO();

        try {
            Path htmlTemplatePath = getHtmlTemplatePath();
            String htmlTemplateContent = Files.readString(htmlTemplatePath);
            StringBuilder newHtmlBodyContent = new StringBuilder(
                    """
                    <h1>CURSOS</h1>
                        <table>
                            <tr>
                                <th>ID</th>
                                <th>NOME</th>
                                <th>CARGA HORÁRIA</th>
                                <th>ID_SUBCATEGORIA</th>
                                <th>NOME_SUBCATEGORIA</th>
                            </tr>
                    """);

            for (CourseHtmlDTO c : courses) {
                newHtmlBodyContent.append("""
                            <tr>
                                <td>%s</td>
                                <td>%s</td>
                                <td>%d</td>
                                <td>%s</td>
                                <td>%s</td>
                            </tr>""".formatted(c.id(),
                        c.name(),
                        c.estimatedHoursToFinish(),
                        c.subcategoryId(),
                        c.subcategoryName()));
            }

            newHtmlBodyContent.append("</table>");

            String newHtmlContent = htmlTemplateContent.replace("$body", newHtmlBodyContent.toString());
            Path newHtmlPath = Path.of("src", "main", "resources", "courses.html");

            Files.writeString(newHtmlPath, newHtmlContent, StandardCharsets.UTF_8);
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Path getHtmlTemplatePath() throws URISyntaxException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL htmlTemplateURL = classLoader.getResource("template.html");
        return Path.of(htmlTemplateURL.toURI());
    }

}
