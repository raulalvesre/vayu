package vayu.models.activties;

import vayu.enums.QuestionType;
import vayu.models.Section;
import vayu.models.activties.base.Activity;

public class Question extends Activity {

    private String wording;
    private QuestionType type;

    public Question(String code,
                    String title,
                    Section section,
                    String wording,
                    QuestionType type) {
        super(code, title, section);
        this.wording = wording;
        this.type = type;
    }

    public String getWording() {
        return wording;
    }

    public void setWording(String wording) {
        this.wording = wording;
    }

    public QuestionType getType() {
        return type;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }
    
}
