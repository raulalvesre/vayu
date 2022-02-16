package vayu.models;

import vayu.models.activties.Question;

import static vayu.services.ValidationService.validateIfIsNullOrBlankString;
import static vayu.services.ValidationService.validateIfItIsNull;

public class Alternative {

    private final String text;
    private int order;
    private final boolean correct;
    private String correctnessJustification;
    private final Question question;


    public Alternative(String text, boolean correct, Question question) {
        validateIfIsNullOrBlankString(this.getClass().getSimpleName(), "text", text);
        validateIfItIsNull(this.getClass().getSimpleName(), "question", question);

        this.text = text;
        this.correct = correct;
        this.question = question;
    }

}
