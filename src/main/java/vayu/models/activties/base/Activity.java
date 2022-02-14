package vayu.models.activties.base;

import vayu.enums.ValidationErrorType;
import vayu.models.Section;
import vayu.services.TextValidationService;
import vayu.services.ValidationErrorMessageService;

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
        if (code == null)
            throw new IllegalArgumentException(ValidationErrorMessageService.getMessage("code", ValidationErrorType.Null));

        if (!TextValidationService.isValidCode(code))
            throw new IllegalArgumentException(ValidationErrorMessageService.getModelCodeMessage());
    }

    private void validateTitle(String title) {
        if (title == null)
            throw new IllegalArgumentException(ValidationErrorMessageService.getMessage("title", ValidationErrorType.Null));

        if (title.isBlank())
            throw new IllegalArgumentException(ValidationErrorMessageService.getMessage("title", ValidationErrorType.Blank));
    }

    private void validateSection(Section section) {
        if (section == null)
            throw new IllegalArgumentException(ValidationErrorMessageService.getMessage("section", ValidationErrorType.Null));
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
