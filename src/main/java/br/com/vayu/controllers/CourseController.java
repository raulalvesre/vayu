package br.com.vayu.controllers;

import br.com.vayu.projections.CourseMinifiedProjection;
import br.com.vayu.services.CourseService;
import br.com.vayu.services.SubcategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/admin/courses")
public class CourseController {

    private final CourseService courseService;
    private final SubcategoryService subcategoryService;

    public CourseController(CourseService courseService, SubcategoryService subcategoryService) {
        this.courseService = courseService;
        this.subcategoryService = subcategoryService;
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
        model.addAttribute("subcategoryDto",
                subcategoryService.getByCodeAndCategoryCodeMinified(categoryCode, subcategoryCode));

        return "course/courseList";
    }

    private int parseInt(Optional<Integer> unparsedPageNumber) {
        return unparsedPageNumber
                       .filter(n -> n > 0)
                       .orElse(1) - 1;
    }

}
