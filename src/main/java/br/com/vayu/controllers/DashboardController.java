package br.com.vayu.controllers;

import br.com.vayu.projections.DashboardCategoryProjection;
import br.com.vayu.projections.DashboardInstructorProjection;
import br.com.vayu.services.CategoryService;
import br.com.vayu.services.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class DashboardController {

    private final CategoryService categoryService;
    private final CourseService courseService;

    @GetMapping("/admin")
    public String redirectToDashBoard() {
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/admin/dashboard")
    public String getDashboard(Model model) {
        List<DashboardCategoryProjection> categories = categoryService.getAllAsDashboardProjectionOrderedDesc();
        DashboardInstructorProjection instructor = courseService.getDashboardInstructorProjection();

        model.addAttribute("categories", categories);
        model.addAttribute("instructor", instructor);

        return "dashboard";
    }

}
