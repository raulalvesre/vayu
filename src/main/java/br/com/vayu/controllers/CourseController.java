package br.com.vayu.controllers;

import br.com.vayu.dto.CourseFormDTO;
import br.com.vayu.models.Course;
import br.com.vayu.projections.CourseMinifiedProjection;
import br.com.vayu.services.CourseService;
import br.com.vayu.services.SubcategoryService;
import br.com.vayu.validation.CourseFormDTOValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/admin/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;
    private final SubcategoryService subcategoryService;
    private final CourseFormDTOValidator courseFormDTOValidator;

    @InitBinder("courseFormDTO")
    void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(courseFormDTOValidator);
    }

    @GetMapping("{categoryCode}/{subcategoryCode}")
    public String getPage(@PathVariable String categoryCode,
                          @PathVariable String subcategoryCode,
                          @RequestParam Optional<Integer> page,
                          Model model) {
        int pageInt = parseInt(page);

        Pageable pageable = PageRequest.of(pageInt, 5);
        Page<CourseMinifiedProjection> coursePage = courseService.getPage(subcategoryCode, pageable);

        model.addAttribute("coursePage", coursePage);
        model.addAttribute("categoryCode", categoryCode);
        model.addAttribute("subcategoryDto",
                subcategoryService.getByCodeAndCategoryCodeMinified(categoryCode, subcategoryCode));

        return "course/courseList";
    }

    private int parseInt(Optional<Integer> unparsedPageNumber) {
        return unparsedPageNumber
                       .filter(n -> n > 0)
                       .orElse(1) - 1;
    }

    @GetMapping("/new")
    public String getCreateForm(CourseFormDTO courseFormDTO, Model model) {
        model.addAttribute("formIsCreate", true);
        model.addAttribute("subcategories", subcategoryService.getAllMinifiedInAlphabeticOrder());
        model.addAttribute("postURL", "/admin/courses");

        return "course/courseForm";
    }

    @PostMapping
    public String create(@Valid CourseFormDTO courseFormDTO,
                         BindingResult bindingResult,
                         Model model) {
        if (bindingResult.hasErrors()) {
            return getCreateForm(courseFormDTO, model);
        }

        Course bdCourse = courseService.save(courseFormDTO);

        String categoryCode = bdCourse.getSubcategory().getCategory().getCode();
        String subcategoryCode = courseFormDTO.getSubcategoryCode();

        return "redirect:/admin/courses/" + categoryCode + "/" + subcategoryCode;
    }

    @GetMapping("/{categoryCode}/{subcategoryCode}/{courseCode}")
    public String getUpdateForm(@PathVariable String categoryCode,
                                @PathVariable String subcategoryCode,
                                @PathVariable String courseCode,
                                Model model) {
        CourseFormDTO courseFormDTO = courseService.getByCodeAsFormDto(courseCode);

        String postURL = "/admin/courses/%s/%s/%s".formatted(categoryCode, subcategoryCode, courseFormDTO.getCode());

        model.addAttribute("courseFormDTO", courseFormDTO);
        model.addAttribute("subcategories", subcategoryService.getAllMinifiedInAlphabeticOrder());
        model.addAttribute("postURL", postURL);
        model.addAttribute("formIsCreate", false);

        return "course/courseForm";
    }

    @PostMapping("/{categoryCode}/{subcategoryCode}/{courseCode}")
    public String edit(@PathVariable String categoryCode,
                       @PathVariable String subcategoryCode,
                       @PathVariable String courseCode,
                       @Valid CourseFormDTO courseFormDTO,
                       BindingResult bindingResult,
                       Model model) {
        if (bindingResult.hasErrors()) {
            return "course/courseForm";
        }

        Course bdCourse = courseService.update(courseFormDTO);

        String newCategoryCode = bdCourse.getSubcategory().getCategory().getCode();
        String newSubcategoryCode = courseFormDTO.getSubcategoryCode();

        return "redirect:/admin/courses/" + newCategoryCode + "/" + newSubcategoryCode;
    }


}
