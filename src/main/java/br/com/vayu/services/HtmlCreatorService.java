package br.com.vayu.services;

import br.com.vayu.models.Category;
import br.com.vayu.models.Course;
import br.com.vayu.models.SubCategory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class HtmlCreatorService {

    public static void generateCategoriesHtml(Collection<Category> categories,
                                              Collection<SubCategory> subCategories,
                                              Collection<Course> courses) throws URISyntaxException, IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL htmlTemplateURL = classLoader.getResource("template.html");
        Path htmlTemplatePath = Path.of(htmlTemplateURL.toURI());

        String htmlContent = Files.readString(htmlTemplatePath);

        StringBuilder newHtmlBodyContent = new StringBuilder();

        categories.stream()
                .sorted(Comparator.comparingInt(Category::getOrder))
                .forEachOrdered(category -> {
                    long numberOfCoursesInCategory = courses.stream()
                            .filter(course -> course.getSubCategory().getCategory().equals(category))
                            .count();

                    long sumOfEstimatedHoursToFinish = courses.stream()
                            .filter(course -> course.getSubCategory().getCategory().equals(category))
                            .mapToInt(Course::getEstimatedHoursToFinish)
                            .sum();

                    newHtmlBodyContent.append(String.format("<h2>%s</h2>", category.getName()));
                    newHtmlBodyContent.append(String.format("<p>%s</p>", category.getDescription()));
                    newHtmlBodyContent.append(String.format("<img src=\"%s\" style=\"max-height: 300px\"></br>", category.getIconPath()));
                    newHtmlBodyContent.append(String.format("<span>Cor: <span style=\"background-color: %s\">ㅤㅤ</span></span>", category.getColorCode()));
                    newHtmlBodyContent.append(String.format("<p>Total de cursos: %s", numberOfCoursesInCategory));
                    newHtmlBodyContent.append(String.format("<p>Total de horas: %s", sumOfEstimatedHoursToFinish));
                    newHtmlBodyContent.append("<h3>Subcategorias</h3>");

                    subCategories.stream()
                            .filter(sb -> sb.isActive() && sb.getCategory().equals(category))
                            .sorted(Comparator.comparingInt(SubCategory::getOrder))
                            .forEachOrdered(sb -> {
                                List<String> coursesInThisSubCategoryNames = courses.stream()
                                        .filter(course -> course.getSubCategory().equals(sb))
                                        .map(Course::getName).toList();

                                newHtmlBodyContent.append(String.format("<h4>%s</h4>", sb.getName()));
                                newHtmlBodyContent.append(String.format("<p>%s</p>", sb.getDescription()));
                                newHtmlBodyContent.append("<h5>Cursos</h5>");
                                newHtmlBodyContent.append(String.join(", ", coursesInThisSubCategoryNames));
                            });
                });

        String newHtmlContent = htmlContent.replace("$body", newHtmlBodyContent);
        Path newHtmlPath = Paths.get(htmlTemplatePath.getParent() +
                String.format("/categories%s.html", LocalDateTime.now()));

        Files.writeString(newHtmlPath, newHtmlContent, StandardCharsets.UTF_8);
    }

}
