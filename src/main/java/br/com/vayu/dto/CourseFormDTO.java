package br.com.vayu.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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

    public CourseFormDTO(String name,
                         String code,
                         Integer estimatedHoursToFinish,
                         boolean visible,
                         String targetAudience,
                         String instructorName,
                         String syllabus,
                         String developedAbilities,
                         String subcategoryCode) {
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

}
