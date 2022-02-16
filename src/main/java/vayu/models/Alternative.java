package vayu.models;

import vayu.models.activties.Question;

import static vayu.services.ValidationService.validateIfIsNullOrBlankString;
import static vayu.services.ValidationService.validateIfItIsNull;

public class Alternative {

    private final String text;
    private int order;
    private final boolean isCorrect;
    private String correctnessJustification;
    private final Question question;


    public Alternative(String text, boolean isCorrect, Question question) {
        validateIfIsNullOrBlankString(this.getClass().getSimpleName(), "text", text);
        validateIfItIsNull(this.getClass().getSimpleName(), "question", question);

        this.text = text;
        this.isCorrect = isCorrect;
        this.question = question;
    }

}
