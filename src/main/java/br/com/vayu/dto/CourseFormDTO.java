package br.com.vayu.dto;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class CourseFormDTO {

    private int id;

    @NotBlank(message = "{name.notblank}")
    private String name;

    @NotBlank(message = "{code.notblank}")
    @Pattern(regexp = "^[a-z0-9-]+$", message = "{code.invalid}")
    private String code;

    @NotNull(message = "{estimatedhourstofinish.notnull}")
    @Range(min = 1, max = 20, message = "{estimatedhourstofinish.range}")
    private Integer estimatedHoursToFinish;

    private boolean visible;
    private String targetAudience;

    @NotBlank(message = "{instructorname.notblank}")
    private String instructorName;

    private String syllabus;
    private String developedAbilities;

    private String subcategoryCode;

    public CourseFormDTO(int id,
                         String name,
                         String code,
                         Integer estimatedHoursToFinish,
                         boolean visible,
                         String targetAudience,
                         String instructorName,
                         String syllabus,
                         String developedAbilities,
                         String subcategoryCode) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.estimatedHoursToFinish = estimatedHoursToFinish;
        this.visible = visible;
        this.targetAudience = targetAudience;
        this.instructorName = instructorName;
        this.syllabus = syllabus;
        this.developedAbilities = developedAbilities;
        this.subcategoryCode = subcategoryCode;
    }

    @Deprecated
    public CourseFormDTO() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getEstimatedHoursToFinish() {
        return estimatedHoursToFinish;
    }

    public void setEstimatedHoursToFinish(Integer estimatedHoursToFinish) {
        this.estimatedHoursToFinish = estimatedHoursToFinish;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
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

    public String getSubcategoryCode() {
        return subcategoryCode;
    }

    public void setSubcategoryCode(String subcategoryCode) {
        this.subcategoryCode = subcategoryCode;
    }

}
