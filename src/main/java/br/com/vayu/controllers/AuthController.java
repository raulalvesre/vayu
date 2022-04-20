package br.com.vayu.controllers;

import br.com.vayu.services.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    private final CategoryService categoryService;

    public AuthController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        model.addAttribute("categories", categoryService.getAllAsLoginPageCategories());

        return "login";
    }

}
