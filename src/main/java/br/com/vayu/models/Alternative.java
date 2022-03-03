package br.com.vayu.models;

import br.com.vayu.services.ValidationService;
import br.com.vayu.models.activities.Question;

public class Alternative {

    private final String text;
    private int order;
    private final boolean correct;
    private String justification;
    private final Question question;


    public Alternative(String text, boolean correct, Question question) {
        ValidationService.validateIfIsBlankString("text", text);
        ValidationService.validateIfItIsNull("question", question);

        this.text = text;
        this.correct = correct;
        this.question = question;
    }

}
