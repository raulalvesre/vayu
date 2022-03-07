package br.com.vayu.services;

import br.com.vayu.models.Category;
import br.com.vayu.models.Course;
import br.com.vayu.models.Subcategory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class SqlCreatorService {

    public static void createDataSqlFile(List<Category> categoryList,
                                         List<Subcategory> subcategoryList,
                                         List<Course> courseList) throws IOException {

        String auxStr = createCategoryInserts(categoryList) +
                        createSubcategoryInserts(subcategoryList) +
                        createCourseInserts(courseList);

        String finalStr = auxStr.replace("'null'", "null");

        Path resourcesPath = Paths.get("src", "main", "resources", "data.sql");
        Files.writeString(resourcesPath, finalStr, StandardCharsets.UTF_8);
    }

    private static String createCategoryInserts(List<Category> categoryList) {
        StringBuilder aux = new StringBuilder();

        categoryList.forEach(c -> {
            aux.append("\nINSERT INTO category\n")
                    .append(String.format("VALUES ('%s', '%s', '%s', '%s', %d, %d, '%s', '%s');\n",
                            c.getCode(),
                            c.getName(),
                            c.getDescription().replace("'", "\\'"),
                            c.getStudyGuide().replace("'", "\\'"),
                            c.isActive() ? 1 : 0,
                            c.getOrder(),
                            c.getIconPath().replace("'", "\\'"),
                            c.getColorCode()));
        });

        return aux.toString();
    }

    private static String createSubcategoryInserts(List<Subcategory> subcategoryList) {
        StringBuilder aux = new StringBuilder();

        subcategoryList.forEach(sb -> {
            aux.append("\nINSERT INTO subcategory\n")
                    .append(String.format("VALUES ('%s', '%s', '%s', '%s', '%s', %d, %d);\n",
                            sb.getCode(),
                            sb.getCategory().getCode(),
                            sb.getName(),
                            sb.getDescription().replace("'", "\\'"),
                            sb.getStudyGuide().replace("'", "\\'"),
                            sb.isActive() ? 1 : 0,
                            sb.getOrder()));
        });

        return aux.toString();
    }

    private static String createCourseInserts(List<Course> courseList) {
        StringBuilder aux = new StringBuilder();

        courseList.forEach(course -> {
            aux.append("\nINSERT INTO course\n")
                    .append(String.format("VALUES ('%s', '%s', '%s', %d, %d, '%s', '%s', '%s', '%s');\n",
                            course.getCode(),
                            course.getSubCategory().getCode(),
                            course.getName(),
                            course.getEstimatedHoursToFinish(),
                            course.isVisible() ? 1 : 0,
                            course.getTargetAudience(),
                            course.getInstructorName().replace("'", "\\'"),
                            course.getSyllabus().replace("'", "\\'"),
                            course.getDevelopedAbilities()).replace("'", "\\'"));
        });

        return aux.toString();
    }

}
