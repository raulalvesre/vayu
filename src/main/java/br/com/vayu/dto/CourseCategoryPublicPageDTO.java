package br.com.vayu.dto;

import br.com.vayu.models.Course;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CourseCategoryPublicPageDTO {

    private String name;
    private int estimatedHoursToFinish;

    public CourseCategoryPublicPageDTO(Course course) {
        this.name = course.getName();
        this.estimatedHoursToFinish = course.getEstimatedHoursToFinish();
    }

}


