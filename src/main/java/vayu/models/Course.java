package vayu.models;

import vayu.utils.TextValidator;

public class Course {

    private String code;
    private String name;
    private int timeToFinish;
    private boolean isVisible;
    private String targetAudience;
    private String instructorName;
    private String syllabus;
    private String developedAbilities;

    public Course(String code,
                  String name,
                  int timeToFinish,
                  String instructorName) {
        setCode(code);
        setName(name);
        setTimeToFinish(timeToFinish);
        isVisible = false;
        setInstructorName(instructorName);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        if (!TextValidator.containsOnlyNumbersAndLowerCaseLetters(code))
            throw new IllegalArgumentException(this.getClass().getSimpleName() + "'s Code should only contain lower case letters, numbers and '-'");

        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException(this.getClass().getSimpleName() + "'s Name should not be null or blank");

        this.name = name;
    }

    public int getTimeToFinish() {
        return timeToFinish;
    }

    public void setTimeToFinish(int timeToFinish) {
        if (timeToFinish < 1 || timeToFinish > 20)
            throw  new IllegalArgumentException(this.getClass().getSimpleName() + "'s time to finish should be between 1 and 20");

        this.timeToFinish = timeToFinish;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public String getTargetAudience() {
        return targetAudience;
    }

    public void setTargetAudience(String targetAudience) {
        this.targetAudience = targetAudience;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        if (instructorName.isBlank())
            throw new IllegalArgumentException(this.getClass().getSimpleName() + "'s instructor's name should not be blank");

        this.instructorName = instructorName;
    }

    public String getSyllabus() {
        return syllabus;
    }

    public void setSyllabus(String syllabus) {
        this.syllabus = syllabus;
    }

    public String getDevelopedAbilities() {
        return developedAbilities;
    }

    public void setDevelopedAbilities(String developedAbilities) {
        this.developedAbilities = developedAbilities;
    }

}
