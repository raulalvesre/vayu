package br.com.vayu.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryFormDTO {

    private int id;

    @NotBlank(message = "{name.notblank}")
    private String name;

    @NotBlank(message = "{code.notblank}")
    @Pattern(regexp = "^[a-z0-9-]+$", message = "{code.invalid}")
    private String code;

    private String description;
    private String studyGuide;
    private boolean active;
    private int order;
    private String iconPath;

    @Pattern(regexp = "^#([a-fA-F0-9]{6}|[a-fA-F0-9]{3})$", message = "{colorcode.invalid}")
    private String colorCode;

}
