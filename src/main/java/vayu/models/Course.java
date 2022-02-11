package vayu.models;

import vayu.utils.TextValidator;

public class Course {

    private final String code;
    private final String name;
    private final int timeToFinish;
    private final boolean isVisible;
    private final String targetAudience;
    private final String instructorName;
    private final String syllabus;
    private final String developedAbilities;

    public Course(String code,
                  String name,
                  int timeToFinish,
                  boolean isVisible,
                  String targetAudience,
                  String instructorName,
                  String syllabus,
                  String developedAbilities) {
        if (!TextValidator.containsOnlyNumbersAndLowerCaseLetters(code))
            throw new IllegalArgumentException(this.getClass().getSimpleName() + "'s Code should only contain lower case letters, numbers and '-'");

        if (name == null || name.isBlank())
            throw new IllegalArgumentException(this.getClass().getSimpleName() + "'s Name should not be null or blank");

        this.code = code;
        this.name = name;
        this.timeToFinish = timeToFinish;
        this.isVisible = isVisible;
        this.targetAudience = targetAudience;
        this.instructorName = instructorName;
        this.syllabus = syllabus;
        this.developedAbilities = developedAbilities;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getTimeToFinish() {
        return timeToFinish;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public String getTargetAudience() {
        return targetAudience;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public String getSyllabus() {
        return syllabus;
    }

    public String getDevelopedAbilities() {
        return developedAbilities;
    }
}
