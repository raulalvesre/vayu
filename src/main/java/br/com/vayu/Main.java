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

        System.out.println();
        printIfThereIsPrivateCourse(courses);

        System.out.println();
        System.out.println("INSTRUCTORS NAMES:");
        printInstructorNames(courses);

        System.out.println();
        System.out.print("QUANTITY OF SUBCATEGORIES WITH DESCRIPTION: ");
        printQuantityOfSubCategoriesWithDescription(subCategories);

        System.out.println();
        printInstructorNameAndHowManyCoursesTheyHave(courses);

    }

    private static void printActiveCategories(List<Category> categories) {
        categories.stream()
                .filter(Category::isActive)
                .forEachOrdered(System.out::println);
    }

    private static void printSubCategoriesWithNoDescription(List<SubCategory> subCategories) {
        subCategories.stream()
                .filter(x -> x.getDescription() == null || x.getDescription().isBlank())
                .forEachOrdered(System.out::println);
    }

    private static void printIfThereIsPrivateCourse(List<Course> courses) {
        long privateCourses = courses.stream()
                .filter(Course::isVisible)
                .count();

        if (privateCourses > 0)
            System.out.println("There is at least one private course");
    }

    private static void printInstructorNames(List<Course> courses) {
        courses.stream()
                .map(Course::getInstructorName)
                .distinct()
                .forEachOrdered(System.out::println);
    }

    private static void printQuantityOfSubCategoriesWithDescription(List<SubCategory> subCategories) {
        long qtdSubCategoriesWithDescription = subCategories.stream()
                .filter(x -> x.getDescription() != null && !x.getDescription().isBlank())
                .count();

        System.out.println(qtdSubCategoriesWithDescription);
    }

    private static void printInstructorNameAndHowManyCoursesTheyHave(List<Course> courses) {
        courses.stream()
                .map(Course::getInstructorName)
                .distinct()
                .forEachOrdered(instructorName -> {
                    long qtdCoursesWithThisInstructor = courses.stream()
                            .filter(course -> course.getInstructorName().equals(instructorName))
                            .count();

                    System.out.printf("%s has %d courses\n", instructorName, qtdCoursesWithThisInstructor);
                });
    }


}
