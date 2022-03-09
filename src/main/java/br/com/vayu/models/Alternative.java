package br.com.vayu.models;

import br.com.vayu.models.activities.Question;

import static br.com.vayu.services.ValidationService.validateIfIsBlankString;
import static br.com.vayu.services.ValidationService.validateIfItIsNull;

public class Alternative {

    private final String text;
    private int order;
    private final boolean correct;
    private String justification;
    private final Question question;


    public Alternative(String text, boolean correct, Question question) {
        validateIfIsBlankString("text", text);
        validateIfItIsNull("question", question);

        this.text = text;
        this.correct = correct;
        this.question = question;
    }

}
