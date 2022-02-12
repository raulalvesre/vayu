package vayu.models;

import vayu.models.activties.Question;

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
        if (text == null || text.isBlank())
            throw new IllegalArgumentException(this.getClass().getSimpleName() + "'s text should not be null or blank");

        if (question == null)
            throw new IllegalArgumentException(this.getClass().getName() + "s question should not be null");

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
