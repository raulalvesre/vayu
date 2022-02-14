package vayu.models.activties;

import vayu.enums.QuestionType;
import vayu.models.Section;
import vayu.models.activties.base.Activity;

public class Question extends Activity {

    private final String wording;
    private final QuestionType type;

    public Question(String code,
                    String title,
                    Section section,
                    String wording,
                    QuestionType type) {
        super(code, title, section);

        if (type == null)
            throw new IllegalArgumentException(this.getClass().getSimpleName() + "'s type should not be null");

        this.wording = wording;
        this.type = type;
    }

    public Question(String code,
                    String title,
                    boolean isActive,
                    int order,
                    Section section,
                    String wording,
                    QuestionType type) {
        super(code, title, isActive, order, section);

        if (type == null)
            throw new IllegalArgumentException(this.getClass().getSimpleName() + "'s type should not be null");

        this.wording = wording;
        this.type = type;
    }

    public String getWording() {
        return wording;
    }

    public QuestionType getType() {
        return type;
    }

}
