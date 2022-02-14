package vayu.models;

import vayu.enums.ValidationErrorType;
import vayu.models.activties.Question;
import vayu.services.ValidationErrorMessageService;

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
        if (text == null)
            throw new IllegalArgumentException(ValidationErrorMessageService.getMessage("text", ValidationErrorType.Null));

        if (text.isBlank())
            throw new IllegalArgumentException(ValidationErrorMessageService.getMessage("text", ValidationErrorType.Blank));

        if (question == null)
            throw new IllegalArgumentException(ValidationErrorMessageService.getMessage("question", ValidationErrorType.Null));

        this.text = text;
        this.isCorrect = isCorrect;
        this.question = question;
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
