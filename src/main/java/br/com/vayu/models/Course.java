package br.com.vayu.models;

import static br.com.vayu.services.ValidationService.*;

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
        validateIfItIsValidCode(code);
        validateIfIsBlankString("name", name);
        validateIfIntIsWithinRange("estimated hours to finish", estimatedHoursToFinish, 1, 20);
        validateIfIsBlankString("instructor name", instructorName);
        validateIfItIsNull("sub category", subCategory);

        this.code = code;
        this.name = name;
        this.estimatedHoursToFinish = estimatedHoursToFinish;
        this.instructorName = instructorName;
        this.subCategory = subCategory;
    }

}
