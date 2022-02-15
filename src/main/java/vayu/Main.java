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

//        var blankUrl = new Video(
//                "code",
//                "intro svelte forms",
//                true,
//                1,
//                theBasicsSvelteForms,
//                "");
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
//                QuestionType.UniqueAnswer);

//        var blankCode = new Question(
//                "",
//                "how to check current form field value in svelte",
//                theBasicsSvelteForms,
//                "how do we check a current form field value in svelte?",
//                QuestionType.UniqueAnswer);

//        var invalidCode = new Question(
//                "AAA",
//                "how to check current form field value in svelte",
//                theBasicsSvelteForms,
//                "how do we check a current form field value in svelte?",
//                QuestionType.UniqueAnswer);
        //endregion

        //region title
//        var nullTitle = new Question(
//                "code",
//                null,
//                theBasicsSvelteForms,
//                "how do we check a current form field value in svelte?",
//                QuestionType.UniqueAnswer);

//        var blankTitle = new Question(
//                "code",
//                "",
//                theBasicsSvelteForms,
//                "how do we check a current form field value in svelte?",
//                QuestionType.UniqueAnswer);
        //endregion

        //region section
//        var nullSection = new Question(
//                "code",
//                "how to check current form field value in svelte",
//                null,
//                "how do we check a current form field value in svelte?",
//                QuestionType.UniqueAnswer);
        //endregion

        //region text
//        var nullText = new Question(
//                "code",
//                "how to check current form field value in svelte",
//                theBasicsSvelteForms,
//                null,
//                QuestionType.UniqueAnswer);
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
                QuestionType.UniqueAnswer);

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
