package br.com.vayu;

import br.com.vayu.enums.QuestionType;
import br.com.vayu.models.Category;
import br.com.vayu.models.Course;
import br.com.vayu.models.Section;
import br.com.vayu.models.SubCategory;
import br.com.vayu.models.activties.Question;
import br.com.vayu.services.CsvParserService;

import java.io.IOException;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {
        Map<String, Category> categories = CsvParserService.getCategoriesMapFromCsv();
        Map<String, SubCategory> subCategories = CsvParserService.getSubCategoriesMapFromCsv(categories);
        Map<String, Course> courses = CsvParserService.getCoursesMapFromCsv(subCategories);

        System.out.println();
        categories.values().forEach(System.out::println);
        System.out.println();
        subCategories.values().forEach(System.out::println);
        System.out.println();
        courses.values().forEach(System.out::println);
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
