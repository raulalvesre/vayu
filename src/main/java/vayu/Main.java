package vayu;

import vayu.enums.QuestionType;
import vayu.models.*;
import vayu.models.activties.Explanation;
import vayu.models.activties.Question;
import vayu.models.activties.Video;

public class Main {

    public static void main(String[] args) {
        Category frontEnd = new Category("abc-123", "front-end");
        Category backEnd = new Category(
                "def-456",
                "back-end",
                "curso back-end",
                "estude java",
                true, 1,
                "pasta/arquivo.jpg",
                "#008000");

        SubCategory svelte = new SubCategory("cod-svelt", "svelt", frontEnd);
        SubCategory springBoot = new SubCategory(
                "cod-spring",
                "spring boot",
                "curso de spring",
                "estude spring",
                true,
                1,
                backEnd);

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
    }

}
