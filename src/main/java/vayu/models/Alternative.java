package vayu.models;

import vayu.models.activties.Question;

import static vayu.services.ValidationService.validateIfIsBlankString;
import static vayu.services.ValidationService.validateIfItIsNull;

public class Alternative {

    private final String text;
    private int order;
    private final boolean correct;
    private String correctnessJustification;
    private final Question question;


    public Alternative(String text, boolean correct, Question question) {
        validateIfIsBlankString("text", text);
        validateIfItIsNull("question", question);

        this.text = text;
        this.correct = correct;
        this.question = question;
    }

}
