package br.com.vayu;

import br.com.vayu.enums.QuestionType;
import br.com.vayu.models.*;
import br.com.vayu.models.activties.Question;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();

        Map<String, Category> categories = getCategoriesMapFromCsv(classloader);
        Map<String, SubCategory> subCategories = getSubCategoriesMapFromCsv(classloader, categories);
        Map<String, Course> courses = getCoursesMapFromCsv(classloader, subCategories);

        categories.values().forEach(System.out::println);
        subCategories.values().forEach(System.out::println);
        courses.values().forEach(System.out::println);
    }

    public static Map<String, Category> getCategoriesMapFromCsv(ClassLoader classLoader) {
        Map<String, Category> categories = new HashMap<>();

        InputStream categoriesInputStream = classLoader.getResourceAsStream("categories.csv");

        try (Scanner lineScanner = new Scanner(categoriesInputStream, StandardCharsets.UTF_8)) {
            lineScanner.nextLine(); // skip first .csv line

            while (lineScanner.hasNext()) {
                Scanner columnScanner = new Scanner(lineScanner.nextLine());
                columnScanner.useDelimiter(",");

                String name = columnScanner.next().trim();
                String code = columnScanner.next().trim();
                String orderStr = columnScanner.next();
                String description = columnScanner.next().trim();
                String activeStr = columnScanner.next().trim();
                String iconPath = columnScanner.next().trim();
                String colorCode = columnScanner.hasNext() ? columnScanner.next().trim() : null;

                Integer order = orderStr.isBlank() ? null : Integer.parseInt(orderStr);
                boolean active = activeStr.equals("ATIVA");

                Category category = new Category(
                        code,
                        name,
                        description,
                        null,
                        active,
                        order,
                        iconPath,
                        colorCode);

                categories.put(code, category);
            }
        }

        return categories;
    }

    public static Map<String, SubCategory> getSubCategoriesMapFromCsv(ClassLoader classLoader,
                                                                      Map<String, Category> categoryMap) {
        Map<String, SubCategory> subCategories = new HashMap<>();

        InputStream subCategoriesInputStream = classLoader.getResourceAsStream("subcategories.csv");

        try (Scanner lineScanner = new Scanner(subCategoriesInputStream, StandardCharsets.UTF_8)) {
            lineScanner.nextLine(); // skip first .csv line

            while (lineScanner.hasNext()) {
                Scanner columnScanner = new Scanner(lineScanner.nextLine());
                columnScanner.useDelimiter(",");

                String name = columnScanner.next().trim();
                String code = columnScanner.next().trim();
                String orderStr = columnScanner.next();
                String description = columnScanner.next().trim();
                String activeStr = columnScanner.next().trim();
                String categoryCode = columnScanner.hasNext() ? columnScanner.next().trim() : null;

                if (categoryCode == null) {
                    System.out.println("Sub Category with invalid Category code could not be created. Please check the .csv!");
                    continue;
                }

                Integer order = orderStr.isBlank() ? null : Integer.parseInt(orderStr);
                boolean active = activeStr.equals("ATIVA");
                Category category = categoryMap.get(categoryCode);

                SubCategory subCategory = new SubCategory(
                        code,
                        name,
                        description,
                        null,
                        active,
                        order,
                        category);

                subCategories.put(code, subCategory);
            }
        }

        return subCategories;
    }

    public static Map<String, Course> getCoursesMapFromCsv(ClassLoader classLoader,
                                                           Map<String, SubCategory> subCategoryMap) {
        Map<String, Course> courses = new HashMap<>();

        InputStream coursesInputStream = classLoader.getResourceAsStream("courses.csv");

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
                String subCategoryCode = columnScanner.hasNext() ? columnScanner.next().trim() : null;

                if (subCategoryCode == null) {
                    System.out.println("Course with invalid Sub Category code could not be created. Please check the .csv!");
                    continue;
                }

                Integer estimatedHoursToFinish = estimatedHoursToFinishStr.isBlank() ? null
                        : Integer.parseInt(estimatedHoursToFinishStr);
                boolean visible = visibleStr.equals("PÚBLICA");
                SubCategory subCategory = subCategoryMap.get(subCategoryCode);

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

                courses.put(code, course);
            }
        }

        return courses;
    }

    //region RaulUnit
    private static void testCategoryValidation() {
        //region code
        //var nullCode = new Category(null, "front-end");
        //var blankCode = new Category("", "front-end");
        //var invalidCode = new Category("AAA", "front-end");
        //endregion

        //region name
        //var nullName = new Category("aaa", null);
        //var blankName = new Category("aaa", "");
        //endregion

        //region colorCode
/*        var nullColorCode = new Category(
                "def-456",
                "back-end",
                "curso back-end",
                "estude java",
                true, 1,
                "pasta/arquivo.jpg",
                null);

        var invalidColorCode = new Category(
                "def-456",
                "back-end",
                "curso back-end",
                "estude java",
                true, 1,
                "pasta/arquivo.jpg",
                "aaa");*/
        //endregion
    }


    private static void testSubCategoryValidation() {
        var frontEnd = new Category("front-end", "front-end");

        //region code
        //var nullCode = new SubCategory(null, "svelt", frontEnd);
        //var blankCode = new SubCategory("", "svelt", frontEnd);
        //var c = new SubCategory("AAA", "svelte", frontEnd);
        //endregion

        //region name
        //var nullName = new SubCategory("a", null, frontEnd);
        //var blankName = new SubCategory("a", "", frontEnd);
        //endregion

        //region category
        //var nullCategory = new SubCategory("a", "a", null);
        //endregion
    }

    private static void testCourseValidation() {
        var frontEnd = new Category("front-end", "front-end");
        var svelte = new SubCategory("svelte", "svelte", frontEnd);

        //region code
        //var nullCode = new Course(null, "svelte", 1, "raul", svelte);
        //var blankCode = new Course("", "svelte", 1, "raul", svelte);
        //var invalidCode = new Course("A", "svelte", 1, "raul", svelte);
        //endregion

        //region name
        //var nullName = new Course("code", null, 1, "raul", svelte);
        //var blankName = new Course("code", "", 1, "raul", svelte);
        //endregion

        //region estimatedHoursToFinish
        /*var lessOneEstimatedHours = new Course(
                "svelte-forms",
                "svelt forms",
                -1,
                "raul",
                svelte);*/

        /*var moreThan20EstimatedHours = new Course(
                "svelte-forms",
                "svelt forms",
                21,
                "raul",
                svelte);*/
        //endregion

        //region instructorName
        /*var nullInstructor = new Course(
                "svelte-forms",
                "svelt forms",
                -1,
                null,
                svelte);*/

        /*var blankInstructor = new Course(
                "svelte-forms",
                "svelt forms",
                21,
                "",
                svelte);*/
        //endregion

        //region sub category
        /*var nullCategory = new Course(
                "svelte-forms",
                "svelt forms",
                1,
                "raul",
                null);*/
        //endregion
    }

    private static void testSectionValidation() {
        var frontEnd = new Category("front-end", "front-end");
        var svelte = new SubCategory("svelte", "svelte", frontEnd);
        var svelteForms = new Course(
                "svelte-forms",
                "svelt forms",
                9,
                "raul",
                svelte);

        //region code
//        var nullCode = new Section(
//                null,
//                "the basics",
//                svelteForms);
//
//        var blankCode = new Section(
//                "",
//                "the basics",
//                svelteForms);
//
//        var invalidCode = new Section(
//                "AAA",
//                "the basics",
//                svelteForms);
        //endregion

        //region name
//        var nullName = new Section(
//                "code",
//                null,
//                svelteForms);
//
//        var blankName = new Section(
//                "code",
//                "",
//                svelteForms);
        //endregion

        //region course
//        var nullCourse = new Section(
//                "code",
//                "name",
//                null);
        //endregion
    }

    private static void testVideoValidation() {
        var frontEnd = new Category("front-end", "front-end");
        var svelte = new SubCategory("svelte", "svelte", frontEnd);
        var svelteForms = new Course(
                "svelte-forms",
                "svelt forms",
                9,
                "raul",
                svelte);
        var theBasicsSvelteForms = new Section(
                "svelt-forms-basics",
                "the basics",
                svelteForms);

        //region code
//        var nullCode = new Video(
//                null,
//                "intro svelte forms",
//                true,
//                1,
//                theBasicsSvelteForms,
//                "uma url");

//        var blankCode = new Video(
//                "",
//                "intro svelte forms",
//                true,
//                1,
//                theBasicsSvelteForms,
//                "uma url");

//        var invalidCode = new Video(
//                "AAA",
//                "intro svelte forms",
//                true,
//                1,
//                theBasicsSvelteForms,
//                "uma url");
        //endregion

        //region title
//        var nullTitle = new Video(
//                "code",
//                null,
//                true,
//                1,
//                theBasicsSvelteForms,
//                "uma url");

//        var blankTitle = new Video(
//                "code",
//                "",
//                true,
//                1,
//                theBasicsSvelteForms,
//                "uma url");
        //endregion

        //region section
//        var nullSection = new Video(
//                "code",
//                "intro svelte forms",
//                true,
//                1,
//                null,
//                "uma url");
        //endregion

        //region URL
        //        var nullUrl = new Video(
//                "code",
//                "intro svelte forms",
//                true,
//                1,
//                theBasicsSvelteForms,
//                null);

//        var invalidURL = new Video(
//                "code",
//                "intro svelte forms",
//                true,
//                1,
//                theBasicsSvelteForms,
//                "invalid URL");
        //endregion
    }

    private static void testExplanationValidation() {
        var frontEnd = new Category("front-end", "front-end");
        var svelte = new SubCategory("svelte", "svelte", frontEnd);
        var svelteForms = new Course(
                "svelte-forms",
                "svelt forms",
                9,
                "raul",
                svelte);
        var theBasicsSvelteForms = new Section(
                "code",
                "the basics",
                svelteForms);

        //region code
//        var nullCode = new Explanation(
//                null,
//                "how svelte forms works",
//                theBasicsSvelteForms,
//                "it just works");

//        var blankCode = new Explanation(
//                "",
//                "how svelte forms works",
//                theBasicsSvelteForms,
//                "it just works");

//        var invalidCode = new Explanation(
//                "AAA",
//                "how svelte forms works",
//                theBasicsSvelteForms,
//                "it just works");
        //endregion

        //region title
//        var nullTitle = new Explanation(
//                "code",
//                null,
//                theBasicsSvelteForms,
//                "it just works");

//        var blankTitle = new Explanation(
//                "code",
//                "",
//                theBasicsSvelteForms,
//                "it just works");
        //endregion

        //region section
//        var a = new Explanation(
//                "code",
//                "title",
//                null,
//                "it just works");
        //endregion

        //region text
//        var nullText = new Explanation(
//                "code",
//                "title",
//                theBasicsSvelteForms,
//                null
//        );

//        var blankTitle = new Explanation(
//                "code",
//                "title",
//                theBasicsSvelteForms,
//                "");
        //endregion
    }

    private static void testQuestionValidation() {
        var frontEnd = new Category("front-end", "front-end");
        var svelte = new SubCategory("svelte", "svelte", frontEnd);
        var svelteForms = new Course(
                "svelte-forms",
                "svelt forms",
                9,
                "raul",
                svelte);
        var theBasicsSvelteForms = new Section(
                "code",
                "the basics",
                svelteForms);

        //region code
//        var nullCode = new Question(
//                null,
//                "how to check current form field value in svelte",
//                theBasicsSvelteForms,
//                "how do we check a current form field value in svelte?",
//                QuestionType.UNIQUE_ANSWER);

//        var blankCode = new Question(
//                "",
//                "how to check current form field value in svelte",
//                theBasicsSvelteForms,
//                "how do we check a current form field value in svelte?",
//                QuestionType.UNIQUE_ANSWER);

//        var invalidCode = new Question(
//                "AAA",
//                "how to check current form field value in svelte",
//                theBasicsSvelteForms,
//                "how do we check a current form field value in svelte?",
//                QuestionType.UNIQUE_ANSWER);
        //endregion

        //region title
//        var nullTitle = new Question(
//                "code",
//                null,
//                theBasicsSvelteForms,
//                "how do we check a current form field value in svelte?",
//                QuestionType.UNIQUE_ANSWER);

//        var blankTitle = new Question(
//                "code",
//                "",
//                theBasicsSvelteForms,
//                "how do we check a current form field value in svelte?",
//                QuestionType.UNIQUE_ANSWER);
        //endregion

        //region section
//        var nullSection = new Question(
//                "code",
//                "how to check current form field value in svelte",
//                null,
//                "how do we check a current form field value in svelte?",
//                QuestionType.UNIQUE_ANSWER);
        //endregion

        //region text
//        var nullText = new Question(
//                "code",
//                "how to check current form field value in svelte",
//                theBasicsSvelteForms,
//                null,
//                QuestionType.UNIQUE_ANSWER);
        //endregion

        //region question type
//        var nullType = new Question(
//                "code",
//                "how to check current form field value in svelte",
//                theBasicsSvelteForms,
//                "how do we check a current form field value in svelte?",
//                null);
        //endregion
    }

    private static void testAlternativeValidation() {
        var frontEnd = new Category("front-end", "front-end");
        var svelte = new SubCategory("svelte", "svelte", frontEnd);
        var svelteForms = new Course(
                "svelte-forms",
                "svelt forms",
                9,
                "raul",
                svelte);
        var theBasicsSvelteForms = new Section(
                "code",
                "the basics",
                svelteForms);

        var howToCheckCurrentFormFieldSvelteForms = new Question(
                "check-form-field-value-svelte-forms",
                "how to check current form field value in svelte",
                theBasicsSvelteForms,
                "how do we check a current form field value in svelte?",
                QuestionType.UNIQUE_ANSWER);

        //region text
//        var nullText = new Alternative(
//                null,
//                false,
//                howToCheckCurrentFormFieldSvelteForms);

//        var blankText = new Alternative(
//                "",
//                false,
//                howToCheckCurrentFormFieldSvelteForms);
        //endregion

        //region question
//        var nullQuestion = new Alternative(
//                "text",
//                false,
//                null);
        //endregion
    }
    //endregion

}
