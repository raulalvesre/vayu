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
        List<Category> categoriesListOrdered = categories.stream()
                .sorted(Comparator.comparingInt(Category::getOrder))
                .toList();

        List<SubCategory> subCategoriesListOrdered = subCategories.stream()
                .sorted(Comparator.comparingInt(SubCategory::getOrder))
                .toList();

        Path htmlTemplatePath = getHtmlTemplatePath();
        String htmlTemplateContent = Files.readString(htmlTemplatePath);

        StringBuilder newHtmlBodyContent = new StringBuilder();

        for (Category category : categoriesListOrdered) {
            long numberOfCoursesInCategory = courses.stream()
                    .filter(course -> course.getSubCategory().getCategory().equals(category))
                    .count();

            long sumOfEstimatedHoursToFinish = courses.stream()
                    .filter(course -> course.getSubCategory().getCategory().equals(category))
                    .mapToInt(Course::getEstimatedHoursToFinish)
                    .sum();

            newHtmlBodyContent.append(String.format("<h2>%s</h2>", category.getName()));
            newHtmlBodyContent.append(String.format("<p>%s</p>", category.getDescription()));
            newHtmlBodyContent.append(String.format("<img src=\"%s\" style=\"max-height: 300px\"></br>",
                    category.getIconPath()));
            newHtmlBodyContent.append(String.format("<span>Cor: <span style=\"background-color: %s\">ㅤㅤ</span></span>",
                    category.getColorCode()));
            newHtmlBodyContent.append(String.format("<p>Total de cursos: %s", numberOfCoursesInCategory));
            newHtmlBodyContent.append(String.format("<p>Total de horas: %s", sumOfEstimatedHoursToFinish));
            newHtmlBodyContent.append("<h3>Subcategorias</h3>");

            subCategoriesListOrdered.stream()
                    .filter(sb -> sb.isActive() && sb.getCategory().equals(category))
                    .forEachOrdered(sb -> {
                        List<String> namesOfCoursesInThisSubcategory = courses.stream()
                                .filter(course -> course.getSubCategory().equals(sb))
                                .map(Course::getName)
                                .toList();

                        newHtmlBodyContent.append(String.format("<h4>%s</h4>", sb.getName()));
                        newHtmlBodyContent.append(String.format("<p>%s</p>", sb.getDescription()));
                        newHtmlBodyContent.append("<h5>Cursos</h5>");
                        newHtmlBodyContent.append(String.join(", ", namesOfCoursesInThisSubcategory));
                    });
        }

        String newHtmlContent = htmlTemplateContent.replace("$body", newHtmlBodyContent);
        Path newHtmlPath = Paths.get(htmlTemplatePath.getParent() +
                String.format("/categories%s.html", LocalDateTime.now()));

        Files.writeString(newHtmlPath, newHtmlContent, StandardCharsets.UTF_8);
    }

    private static Path getHtmlTemplatePath() throws URISyntaxException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL htmlTemplateURL = classLoader.getResource("template.html");
        return Path.of(htmlTemplateURL.toURI());
    }

}
