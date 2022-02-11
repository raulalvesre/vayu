package vayu.models.activties.base;

import vayu.models.Section;
import vayu.utils.TextValidator;

public abstract class Activity {

    private final String code;
    private final String title;
    private final boolean active;
    private final int order;
    private final Section section;

    public Activity(String code,
                    String title,
                    boolean active,
                    int order,
                    Section section) {
        if (!TextValidator.containsOnlyNumbersAndLowerCaseLetters(code))
            throw new IllegalArgumentException(this.getClass().getSimpleName() + "'s Code should only contain lower case letters, numbers and '-'");

        this.code = code;
        this.title = title;
        this.active = active;
        this.order = order;
        this.section = section;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public boolean isActive() {
        return active;
    }

    public int getOrder() {
        return order;
    }

    public Section getSection() {
        return section;
    }
}
