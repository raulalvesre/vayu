package vayu;

import vayu.enums.QuestionType;
import vayu.models.*;
import vayu.models.activties.Explanation;
import vayu.models.activties.Question;
import vayu.models.activties.Video;

public class Main {

    public static void main(String[] args) {
        //region Category
        Category frontEnd = new Category("abc-123", "front-end");
        Category backEnd = new Category(
                "def-456",
                "back-end",
                "curso back-end",
                "estude java",
                true, 1,
                "pasta/arquivo.jpg",
                "#008000");
        //endregion

        //region SubCategory
        SubCategory svelte = new SubCategory("cod-svelt", "svelt", frontEnd);
        SubCategory springBoot = new SubCategory(
                "cod-spring",
                "spring boot",
                "curso de spring",
                "estude spring",
                true,
                1,
                backEnd);
        //endregion

        //region Course
        Course svelteForms = new Course(
                "svelte-forms",
                "svelt forms",
                9,
                "raul",
                svelte);

        Course springValidation = new Course(
                "spring-validation",
                "spring validation",
                9,
                true,
                "gente legal",
                "raul",
                "spring validation",
                "validacao no spring",
                springBoot);
        //endregion

        //region Section
        Section theBasicsSvelteForms = new Section(
                "svelt-forms-basics",
                "the basics",
                svelteForms);

        Section theBasicsSpringValidation = new Section(
                "spring-boot-basics",
                "the basics",
                1,
                true,
                false,
                springValidation);
        //endregion

        //region Video
        Video introSveltForms = new Video(
                "01-svelt-forms",
                "intro svelte forms",
                true,
                1,
                theBasicsSvelteForms,
                "uma url");

        Video introSpringValidation = new Video(
                "01-spring-validation",
                "intro spring validation",
                true,
                1,
                theBasicsSpringValidation,
                "outra url",
                8,
                "intro spring validation");
        //endregion

        //region Explanation
        Explanation howFormsWorksInSvelte = new Explanation(
                "forms-in-svelte",
                "how svelte forms works",
                theBasicsSvelteForms,
                "it just works");

        Explanation basicAnnotationsInSpringValidation = new Explanation(
                "basic-annotations-spring-validation",
                "basic annotations in spring validation",
                true,
                1,
                theBasicsSpringValidation,
                "this are the most basic annotations...");
        //endregion

        //region Question
        Question howToCheckCurrentFormFieldSvelteForms = new Question(
                "check-form-field-value-svelte-forms",
                "how to check current form field value in svelte",
                theBasicsSvelteForms,
                "how do we check a current form field value in svelte?",
                QuestionType.UniqueAnswer);

        Question howToMakeFieldRequiredInSpringValidation = new Question(
                "make-field-required-spring-validation",
                "how to make a field required in spring validation",
                true,
                1,
                theBasicsSpringValidation,
                "how do make a field required in spring validation?",
                QuestionType.UniqueAnswer);
        //endregion

        //region Alternative
        Alternative justBindIt = new Alternative(
                "just bind it",
                false,
                howToCheckCurrentFormFieldSvelteForms);

        Alternative notNullIt = new Alternative(
                "just use @NotNull",
                1,
                false,
                "yes, this is correct (Y)",
                howToMakeFieldRequiredInSpringValidation);
        //endregion

        //region tests
        testCategoryValidation();
        testSubCategoryValidation();
        testCourseValidation();
        testSectionValidation();
        testVideoValidation();
        testExplanationValidation();
        testQuestionValidation();
        testAlternativeValidation();
        //endregion
    }

    //region RaulUnit
    private static void testCategoryValidation() {
        //region code
        //var a = new Category(null, "front-end");
        //var b = new Category("", "front-end");
        //var c = new Category("AAA", "front-end");
        //endregion

        //region name
        //var a = new Category("aaa", null);
        //var b = new Category("aaa", "");
        //endregion

        //region colorCode
/*        var a = new Category(
                "def-456",
                "back-end",
                "curso back-end",
                "estude java",
                true, 1,
                "pasta/arquivo.jpg",
                null);

        var b = new Category(
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
        //var a = new SubCategory(null, "svelt", frontEnd);
        //var b = new SubCategory("", "svelt", frontEnd);
        //var c = new SubCategory("AAA", "svelte", frontEnd);
        //endregion

        //region name
        //var a = new SubCategory("a", null, frontEnd);
        //var b = new SubCategory("a", "", frontEnd);
        //endregion

        //region category
        //var a = new SubCategory("a", "a", null);
        //endregion
    }

    private static void testCourseValidation() {
        var frontEnd = new Category("front-end", "front-end");
        var svelte = new SubCategory("svelte", "svelte", frontEnd);

        //region code
        //var a = new Course(null, "svelte", 1, "raul", svelte);
        //var b = new Course("", "svelte", 1, "raul", svelte);
        //var c = new Course("A", "svelte", 1, "raul", svelte);
        //endregion

        //region name
        //var a = new Course("code", null, 1, "raul", svelte);
        //var b = new Course("code", "", 1, "raul", svelte);
        //endregion

        //region estimatedHoursToFinish
        /*var a = new Course(
                "svelte-forms",
                "svelt forms",
                -1,
                "raul",
                svelte);*/

        /*var b = new Course(
                "svelte-forms",
                "svelt forms",
                21,
                "raul",
                svelte);*/
        //endregion

        //region instructorName
        /*var a = new Course(
                "svelte-forms",
                "svelt forms",
                -1,
                null,
                svelte);*/

        /*var b = new Course(
                "svelte-forms",
                "svelt forms",
                21,
                "",
                svelte);*/
        //endregion

        //region sub category
        /*var a = new Course(
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
        var a = new Section(
                null,
                "the basics",
                svelteForms);
        //endregion

        //region name
        //var a = new Course("code", null, 1, "raul", svelte);
        //var b = new Course("code", "", 1, "raul", svelte);
        //endregion
    }

    private static void testVideoValidation() {

    }

    private static void testExplanationValidation() {

    }

    private static void testQuestionValidation() {

    }

    private static void testAlternativeValidation() {

    }
    //endregion

}
