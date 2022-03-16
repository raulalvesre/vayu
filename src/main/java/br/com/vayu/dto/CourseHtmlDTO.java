package br.com.vayu.dto;

public record CourseHtmlDTO(int id,
                            String name,
                            int estimatedHoursToFinish,
                            int subcategoryId,
                            String subcategoryName) {
}
