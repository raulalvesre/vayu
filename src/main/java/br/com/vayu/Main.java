package br.com.vayu;

import br.com.vayu.enums.QuestionType;
import br.com.vayu.models.Category;
import br.com.vayu.models.Course;
import br.com.vayu.models.Section;
import br.com.vayu.models.SubCategory;
import br.com.vayu.models.activities.Question;
import br.com.vayu.services.CsvParserService;
import br.com.vayu.services.HtmlCreatorService;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException, URISyntaxException {
        List<Category> categories = CsvParserService.getCategoriesMapFromCsv();
        List<SubCategory> subCategories = CsvParserService.getSubCategoriesMapFromCsv(categories);
        List<Course> courses = CsvParserService.getCoursesMapFromCsv(subCategories);

        HtmlCreatorService.generateCategoriesHtml(
                categories,
                subCategories,
                courses);

        System.out.println();
        categories.forEach(System.out::println);
        System.out.println();
        subCategories.forEach(System.out::println);
        System.out.println();
        courses.forEach(System.out::println);
    }

    public static void printActiveCategories(List<Category> categories) {
        categories.stream()
                .filter(Category::isActive)
                .forEachOrdered(System.out::println);
    }

}
