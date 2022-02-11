package vayu.models.activties.base;

import vayu.models.Section;
import vayu.utils.TextValidator;

public abstract class Activity {

    private String code;
    private String title;
    private boolean isActive;
    private int order;
    private Section section;

    public Activity(String code,
                    String title,
                    Section section) {
        setCode(code);
        setTitle(title);
        this.isActive = false;
        this.section = section;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        if (code == null)
            throw new IllegalArgumentException(this.getClass().getSimpleName() + "'s Code should not be null");

        if (!TextValidator.containsOnlyNumbersAndLowerCaseLetters(code))
            throw new IllegalArgumentException(this.getClass().getSimpleName() + "'s Code should only contain lower case letters, numbers and '-'");

        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.isBlank())
            throw new IllegalArgumentException(this.getClass().getSimpleName() + "'s Text should not be null or blank");

        this.title = title;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }
}
