package vayu.models;

import vayu.models.activties.Question;

public class Alternative {

    private String text;
    private int order;
    private boolean isCorrect;
    private String correctnessJustification;
    private Question question;

    public Alternative(String text, boolean isCorrect, Question question) {
        setText(text);
        this.isCorrect = isCorrect;
        setQuestion(question);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        if (text == null || text.isBlank())
            throw new IllegalArgumentException(this.getClass().getSimpleName() + "'s Text should not be null or blank");

        this.text = text;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    public String getCorrectnessJustification() {
        return correctnessJustification;
    }

    public void setCorrectnessJustification(String correctnessJustification) {
        this.correctnessJustification = correctnessJustification;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        if (question == null)
            throw new IllegalArgumentException(this.getClass().getName() + "s Question should not be null");

        this.question = question;
    }

}
