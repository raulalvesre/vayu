package br.com.vayu.services;

import br.com.vayu.models.Category;
import br.com.vayu.models.Course;
import br.com.vayu.models.Subcategory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class HtmlCreatorService {

    public static void generateCategoriesHtml(Collection<Category> categories,
                                              Collection<Subcategory> subCategories,
                                              Collection<Course> courses) throws URISyntaxException, IOException {
        List<Subcategory> subCategoriesListOrdered = subCategories.stream()
                .sorted(Comparator.comparingInt(Subcategory::getOrder))
                .toList();

        Path htmlTemplatePath = getHtmlTemplatePath();
        String htmlTemplateContent = Files.readString(htmlTemplatePath);

        StringBuilder newHtmlBodyContent = new StringBuilder();

        categories.stream()
                .sorted(Comparator.comparingInt(Category::getOrder))
                .forEachOrdered(category -> {
                    long numberOfCoursesInCategory = getCoursesRegisteredInCategory(courses, category);
                    long sumOfEstimatedHoursToFinish = getSumOfCoursesEstimatedHoursToFinishInCategory(courses, category);

                    newHtmlBodyContent.append(String.format("<h2>%s</h2>", category.getName()))
                            .append(String.format("<p>%s</p>", category.getDescription()))
                            .append(String.format("<img src=\"%s\" style=\"max-height: 300px\"></br>",
                                    category.getIconPath()))
                            .append(String.format("<span>Cor: <span style=\"background-color: %s\">ㅤㅤ" +
                                    "</span></span>", category.getColorCode()))
                            .append(String.format("<p>Total de cursos: %s", numberOfCoursesInCategory))
                            .append(String.format("<p>Total de horas: %s", sumOfEstimatedHoursToFinish))
                            .append("<h3>Subcategorias</h3>");

                    subCategoriesListOrdered.stream()
                            .filter(sb -> sb.isActive() && sb.getCategory().equals(category))
                            .forEachOrdered(sb -> {
                                List<String> namesOfCoursesInThisSubcategory = getNamesOfCoursesInSubcategory(courses, sb);

                                newHtmlBodyContent.append(String.format("<h4>%s</h4>", sb.getName()))
                                        .append(String.format("<p>%s</p>", sb.getDescription()))
                                        .append("<h5>Cursos</h5>")
                                        .append(String.join(", ", namesOfCoursesInThisSubcategory));
                            });
                });

        String newHtmlContent = htmlTemplateContent.replace("$body", newHtmlBodyContent);
        Path newHtmlPath = Paths.get(htmlTemplatePath.getParent() + "/categories.html");

        Files.writeString(newHtmlPath, newHtmlContent, StandardCharsets.UTF_8);
    }

    private static Path getHtmlTemplatePath() throws URISyntaxException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL htmlTemplateURL = classLoader.getResource("template.html");
        return Path.of(htmlTemplateURL.toURI());
    }

    private static long getCoursesRegisteredInCategory(Collection<Course> courses, Category category) {
        return courses.stream()
                .filter(course -> course.getSubCategory().getCategory().equals(category))
                .count();
    }

    private static long getSumOfCoursesEstimatedHoursToFinishInCategory(Collection<Course> courses, Category category) {
        return courses.stream()
                .filter(course -> course.getSubCategory().getCategory().equals(category))
                .mapToInt(Course::getEstimatedHoursToFinish)
                .sum();
    }

    private static List<String> getNamesOfCoursesInSubcategory(Collection<Course> courses, Subcategory subCategory) {
        return courses.stream()
                .filter(course -> course.getSubCategory().equals(subCategory))
                .map(Course::getName)
                .toList();
    }

}
