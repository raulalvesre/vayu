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

        Path resourcesPath = Paths.get("target", "classes", "data.sql");
        Files.writeString(resourcesPath, finalStr, StandardCharsets.UTF_8);
    }

    private static String createCategoryInserts(List<Category> categoryList) {
        StringBuilder aux = new StringBuilder();

        categoryList.forEach(c -> {
            aux.append("\nINSERT INTO category\n")
                    .append(String.format("VALUES ('%s', '%s', '%s', '%s', %d, %d, '%s', '%s');\n",
                            c.getCode(),
                            c.getName(),
                            c.getDescription() != null ? c.getDescription().replace("'", "\\'")
                                    : null,
                            c.getStudyGuide() != null ? c.getDescription().replace("'", "\\'")
                                    : null,
                            c.isActive() ? 1 : 0,
                            c.getOrder(),
                            c.getIconPath() != null ? c.getIconPath().replace("'", "\\'") : null,
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
                            sb.getDescription() != null ? sb.getDescription().replace("'", "\\'")
                                    : null,
                            sb.getStudyGuide() != null ? sb.getStudyGuide().replace("'", "\\'")
                                    : null,
                            sb.isActive() ? 1 : 0,
                            sb.getOrder()));
        });

        return aux.toString();
    }

    private static String createCourseInserts(List<Course> courseList) {
        StringBuilder aux = new StringBuilder();

        courseList.forEach(c -> {
            aux.append("\nINSERT INTO course\n")
                    .append(String.format("VALUES ('%s', '%s', '%s', %d, %d, '%s', '%s', '%s', '%s');\n",
                            c.getCode(),
                            c.getSubCategory().getCode(),
                            c.getName(),
                            c.getEstimatedHoursToFinish(),
                            c.isVisible() ? 1 : 0,
                            c.getTargetAudience(),
                            c.getInstructorName() != null ? c.getInstructorName().replace("'", "\\'")
                                    : null,
                            c.getSyllabus() != null ? c.getSyllabus().replace("'", "\\'") : null,
                            c.getDevelopedAbilities() != null ?
                                    c.getDevelopedAbilities().replace("'", "\\'") : null));
        });

        return aux.toString();
    }

}
