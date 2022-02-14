package vayu.models.activties.base;

import vayu.enums.ValidationErrorType;
import vayu.models.Section;
import vayu.services.ValidationService;
import vayu.services.ValidationErrorMessageService;

import static vayu.services.ValidationService.*;

public abstract class Activity {

    private final String code;
    private final String title;
    private boolean isActive;
    private int order;
    private final Section section;

    public Activity(String code,
                    String title,
                    boolean isActive,
                    int order,
                    Section section) {
        this(code, title, section);

        this.isActive = isActive;
        this.order = order;
    }

    public Activity(String code,
                    String title,
                    Section section) {
        validateCode(code);
        validateTitle(title);
        validateSection(section);

        this.code = code;
        this.title = title;
        this.isActive = false;
        this.section = section;
    }

    private void validateCode(String code) {
        validateIfItIsNull("code", code);
        validateIfIsBlankString("code", code);
        validateIfItIsValidCode(code);
    }

    private void validateTitle(String title) {
        validateIfItIsNull("title", title);
        validateIfIsBlankString("title", title);
    }

    private void validateSection(Section section) {
        validateIfItIsNull("section", section);
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public boolean isActive() {
        return isActive;
    }

    public int getOrder() {
        return order;
    }

    public Section getSection() {
        return section;
    }

}
