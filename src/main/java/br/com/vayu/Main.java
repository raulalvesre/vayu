package br.com.vayu;

import br.com.vayu.models.Category;
import br.com.vayu.models.Course;
import br.com.vayu.models.SubCategory;
import br.com.vayu.services.CsvParserService;
import br.com.vayu.services.HtmlCreatorService;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static br.com.vayu.helpers.CategoryCollectionHelper.*;
import static br.com.vayu.helpers.CourseCollectionHelper.*;
import static br.com.vayu.helpers.SubCategoryCollectionHelper.*;

public class Main {

    public static void main(String[] args) throws IOException, URISyntaxException {
        List<Category> categories = CsvParserService.getCategoriesListFromCsv();
        List<SubCategory> subCategories = CsvParserService.getSubCategoriesListFromCsv(categories);
        List<Course> courses = CsvParserService.getCoursesListFromCsv(subCategories);

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
        printActiveCategories(categories);

        System.out.println();
        printSubCategoriesWithNoDescription(subCategories);

        System.out.println();
        printIfThereIsPrivateCourse(courses);

        System.out.println();
        printInstructorNames(courses);

        System.out.println();
        printQuantityOfActiveSubCategoriesWithDescription(subCategories);

        System.out.println();
        printInstructorNameAndHowManyCoursesTheyHave(courses);
    }

}
