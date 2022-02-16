package vayu.models;

import static vayu.services.ValidationService.*;

public class Course {

    private final String code;
    private final String name;
    private final int estimatedHoursToFinish;
    private boolean visible;
    private String targetAudience;
    private final String instructorName;
    private String syllabus;
    private String developedAbilities;
    private final SubCategory subCategory;

    public Course(String code,
                  String name,
                  int estimatedHoursToFinish,
                  String instructorName,
                  SubCategory subCategory) {
        validateIfItIsValidCode(this.getClass().getSimpleName(), code);
        validateIfIsNullOrBlankString(this.getClass().getSimpleName(), "name", name);
        validateIfIntIsWithinRange(this.getClass().getSimpleName(), "estimated hours to finish", estimatedHoursToFinish, 1, 20);
        validateIfIsNullOrBlankString(this.getClass().getSimpleName(), "instructor name", instructorName);
        validateIfItIsNull(this.getClass().getSimpleName(), "sub category", subCategory);

        this.code = code;
        this.name = name;
        this.estimatedHoursToFinish = estimatedHoursToFinish;
        this.instructorName = instructorName;
        this.subCategory = subCategory;
    }

}
