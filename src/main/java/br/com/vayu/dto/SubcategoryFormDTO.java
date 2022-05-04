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
public class SubcategoryFormDTO {

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

    @NotBlank(message = "{categorycode.notblank}")
    private String categoryCode;

}

