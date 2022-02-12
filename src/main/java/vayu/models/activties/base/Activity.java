package vayu.models.activties.base;

import vayu.models.Section;
import vayu.utils.TextValidator;

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
        if (code == null)
            throw new IllegalArgumentException(this.getClass().getSimpleName() + "'s code should not be null");

        if (!TextValidator.containsOnlyNumbersAndLowerCaseLetters(code))
            throw new IllegalArgumentException(this.getClass().getSimpleName() + "'s code should only contain lower case letters, numbers and '-'");

        if (title == null || title.isBlank())
            throw new IllegalArgumentException(this.getClass().getSimpleName() + "'s text should not be null or blank");

        if (section == null)
            throw new IllegalArgumentException(this.getClass().getSimpleName() + "'s section should not be null");

        this.code = code;
        this.title = title;
        this.isActive = false;
        this.section = section;
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
