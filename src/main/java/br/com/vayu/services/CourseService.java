package br.com.vayu.services;

import br.com.vayu.models.Course;
import br.com.vayu.models.Subcategory;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CourseService {

    public static List<Course> getCourseListFromCsv(String resourcesFolderPath, List<Subcategory> subcategoryList) {
        List<Course> courses = new ArrayList<>();

        InputStream coursesInputStream = getInputStreamFromResources(resourcesFolderPath);
        if (coursesInputStream == null)
            throw new IllegalArgumentException(resourcesFolderPath + ".csv does not exist!");

        try (Scanner lineScanner = new Scanner(coursesInputStream, StandardCharsets.UTF_8)) {
            lineScanner.nextLine(); // skip first .csv line

            while (lineScanner.hasNext()) {
                Scanner columnScanner = new Scanner(lineScanner.nextLine());
                columnScanner.useDelimiter(",");

                String name = columnScanner.next().trim();
                String code = columnScanner.next().trim();
                String estimatedHoursToFinishStr = columnScanner.next();
                String visibleStr = columnScanner.next();
                String targetAudience = columnScanner.next().trim();
                String instructorName = columnScanner.next().trim();
                String syllabus = columnScanner.next().trim();
                String developedAbilities = columnScanner.next().trim();
                String subCategoryCode = columnScanner.next().trim();

                int estimatedHoursToFinish = estimatedHoursToFinishStr.isBlank() ? 0
                        : Integer.parseInt(estimatedHoursToFinishStr);
                boolean visible = visibleStr.equals("PÚBLICA");
                Subcategory subCategory = SubcategoryService.getSubcategoryByCode(subcategoryList, subCategoryCode);

                Course course = new Course(
                        code,
                        name,
                        estimatedHoursToFinish,
                        visible,
                        targetAudience,
                        instructorName,
                        syllabus,
                        developedAbilities,
                        subCategory);

                courses.add(course);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid .csv, please check!!!");
        }

        return courses;
    }

    private static InputStream getInputStreamFromResources(String fileName) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        return classLoader.getResourceAsStream(fileName);
    }

    public static void printIfThereIsPrivateCourse(List<Course> courses) {
        System.out.println("PRIVATE COURSES:");

        courses.stream()
                .filter(c -> !c.isVisible())
                .forEachOrdered(System.out::println);
    }

    public static void printInstructorNames(List<Course> courses) {
        System.out.println("INSTRUCTORS NAMES:");

        courses.stream()
                .map(Course::getInstructorName)
                .distinct()
                .forEachOrdered(System.out::println);
    }

    public static void printInstructorNameAndHowManyCoursesTheyHave(List<Course> courses) {
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
