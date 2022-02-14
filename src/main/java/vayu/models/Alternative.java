package vayu.models;

import vayu.enums.ValidationErrorType;
import vayu.models.activties.Question;
import vayu.services.ValidationErrorMessageService;

import static vayu.services.ValidationService.validateIfIsBlankString;
import static vayu.services.ValidationService.validateIfItIsNull;

public class Alternative {

    private final String text;
    private int order;
    private final boolean isCorrect;
    private String correctnessJustification;
    private final Question question;

    public Alternative(String text,
                       int order,
                       boolean isCorrect,
                       String correctnessJustification,
                       Question question) {
        this(text, isCorrect, question);

        this.order = order;
        this.correctnessJustification = correctnessJustification;
    }

    public Alternative(String text, boolean isCorrect, Question question) {
        validateText(text);
        validateQuestion(question);

        this.text = text;
        this.isCorrect = isCorrect;
        this.question = question;
    }

    private void validateText(String text) {
        validateIfItIsNull("text", text);
        validateIfIsBlankString("text", text);
    }

    private void validateQuestion(Question question) {
        validateIfItIsNull("question", question);
    }

    public String getText() {
        return text;
    }

    public int getOrder() {
        return order;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public String getCorrectnessJustification() {
        return correctnessJustification;
    }

    public Question getQuestion() {
        return question;
    }

}
