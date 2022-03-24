package br.com.vayu.services;

import br.com.vayu.dao.CategoryDAO;
import br.com.vayu.dao.CourseDAO;
import br.com.vayu.dao.SubcategoryDAO;
import br.com.vayu.models.Category;
import br.com.vayu.models.Subcategory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class HtmlCreatorService {

    public static void generateCoursesHtml(CategoryDAO categoryDAO,
                                           SubcategoryDAO subcategoryDAO,
                                           CourseDAO courseDAO) {
        var activeCategoriesInOrder = categoryDAO.findAllByActiveTrueInOrder();
        var activeSubcategoriesInOrder = subcategoryDAO.findAllByActiveTrueInOrder();
        var visibleCourses = courseDAO.findAllByVisibleTrue();

        try {
            Path htmlTemplatePath = getHtmlTemplatePath();
            String htmlTemplateContent = Files.readString(htmlTemplatePath);
            StringBuilder newHtmlBodyContent = new StringBuilder(
                    """
                            <h1>CATEGORIAS ATIVAS</h1>
                            """);

            for (Category ct : activeCategoriesInOrder) {
                newHtmlBodyContent.append("""
                        <img src="%s" style="max-height: 300px"></br>
                        <h1><b>%s</b> <span style="background-color: %s">ㅤㅤ</span> </h1>
                        <p><b>Código:</b> %s</p>
                        <p><b>Descrição:</b> %s</p>
                        <p><b>Guia de estudo:</b> %s</p>""".formatted(ct.getIconPath(),
                        ct.getName().toUpperCase(),
                        ct.getColorCode(),
                        ct.getCode(),
                        ct.getDescription(),
                        ct.getStudyGuide()));

                newHtmlBodyContent.append("<h2>Subcategorias:</h2>");

                activeSubcategoriesInOrder.stream()
                        .filter(sb -> sb.getCategory().equals(ct))
                        .forEachOrdered(sb -> {
                            newHtmlBodyContent.append("""
                                    <h2>=> %s</h2>
                                    <p><b>Código:</b> %s</p>
                                    <p><b>Descrição:</b> %s</p>
                                    <p><b>Guia de estudo:</b> %s</p>
                                    """.formatted(sb.getName(),
                                    sb.getCode(),
                                    sb.getDescription(),
                                    sb.getStudyGuide()
                            ));

                            newHtmlBodyContent.append("<h2>Cursos:</h2>");

                            visibleCourses.stream()
                                    .filter(c -> c.getSubcategory().equals(sb))
                                    .forEachOrdered(c -> {
                                        newHtmlBodyContent.append("""
                                                <h3>=> %s</h3>
                                                <p><b>Código:</b> %s</p>
                                                <p><b>Carga Horária:</b> %s</p>
                                                <p><b>Público alvo:</b> %s</p>
                                                <p><b>Instrutor:</b> %s</p>
                                                <p><b>Ementa:</b></p>
                                                <p>%s</p>
                                                <p><b>Habilidades desenvolvidas:</b></p>
                                                <p>%s</p>
                                                """.formatted(c.getName(),
                                                c.getCode(),
                                                c.getEstimatedHoursToFinish(),
                                                c.getTargetAudience(),
                                                c.getInstructorName(),
                                                c.getSyllabus(),
                                                c.getDevelopedAbilities()));
                                    });
                        });
            }

            newHtmlBodyContent.append("<h2>Nome das subcategorias sem descrição:</h2>");
            activeSubcategoriesInOrder.stream()
                    .filter(sb -> sb.getDescription() == null || sb.getDescription().isBlank())
                    .map(Subcategory::getName)
                    .forEachOrdered(x -> {
                        newHtmlBodyContent.append(x)
                                .append("; ");
                    });

            String newHtmlContent = htmlTemplateContent.replace("$body", newHtmlBodyContent.toString());
            newHtmlContent = newHtmlContent.replace("null", "");
            Path newHtmlPath = Path.of("src", "main", "resources", "semana6.html");

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