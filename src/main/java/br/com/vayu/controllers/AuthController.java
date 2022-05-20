package br.com.vayu.controllers;

import br.com.vayu.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final CategoryService categoryService;

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        model.addAttribute("categories", categoryService.getAllForLoginPage());

        return "login";
    }

}
