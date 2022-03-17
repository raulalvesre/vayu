package br.com.vayu.dto;

public record CreateCourseDTO(String code,
                              String name,
                              int estimatedHoursToFinish,
                              boolean visible,
                              String targetAudience,
                              String instructorName,
                              String syllabus,
                              String developedAbilities,
                              int subcategoryId) {
}