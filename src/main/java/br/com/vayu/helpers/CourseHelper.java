package br.com.vayu.helpers;

import br.com.vayu.models.Course;

import java.util.Collection;

public class CourseHelper {

    public static void printIfThereIsPrivateCourse(Collection<Course> courses) {
        System.out.println("PRIVATE COURSES:");

        courses.stream()
                .filter(Course::isVisible)
                .forEachOrdered(System.out::println);
    }

    public static void printInstructorNames(Collection<Course> courses) {
        System.out.println("INSTRUCTORS NAMES:");

        courses.stream()
                .map(Course::getInstructorName)
                .distinct()
                .forEachOrdered(System.out::println);
    }

    public static void printInstructorNameAndHowManyCoursesTheyHave(Collection<Course> courses) {
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
