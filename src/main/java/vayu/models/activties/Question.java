package vayu.models.activties;

import vayu.enums.QuestionType;
import vayu.models.Section;
import vayu.models.activties.base.Activity;

public class Question extends Activity {

    private String wording;
    private QuestionType type;

    public Question(String code, String title, boolean active, int order, Section section) {
        super(code, title, active, order, section);
    }

}
