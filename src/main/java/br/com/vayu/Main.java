package br.com.vayu;

import br.com.vayu.models.Category;
import br.com.vayu.models.Course;
import br.com.vayu.models.SubCategory;
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

        System.out.println();
        System.out.println("ACTIVE CATEGORIES:");
        printActiveCategories(categories);

        System.out.println();
        System.out.println("SUB CATEGORIES WITHOUT DESCRIPTION");
        printSubCategoriesWithNoDescription(subCategories);
    }

    public static void printActiveCategories(List<Category> categories) {
        categories.stream()
                .filter(Category::isActive)
                .forEachOrdered(System.out::println);
    }

    public static void printSubCategoriesWithNoDescription(List<SubCategory> subCategories) {
        subCategories.stream()
                .filter(x -> x.getDescription() == null || x.getDescription().isBlank())
                .forEachOrdered(System.out::println);
    }



}
