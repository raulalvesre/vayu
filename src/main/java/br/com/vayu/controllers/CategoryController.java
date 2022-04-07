package br.com.vayu.controllers;

import br.com.vayu.dto.CategoryFormDTO;
import br.com.vayu.services.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String getCategories(Model model) {
        var categories = categoryService.getCategoryManagementDtoListInOrder();
        model.addAttribute("categories", categories);
        return "categoryList";
    }

    @GetMapping("/new")
    public String getCreateForm(CategoryFormDTO categoryFormDTO, Model model) {
        model.addAttribute("formIsCreate", true);
        model.addAttribute("postURL", "/admin/categories");

        return "categoryForm";
    }

    @PostMapping
    public String createCategory(@Valid CategoryFormDTO createCategoryDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "categoryForm";
        }

        categoryService.createCategory(createCategoryDTO);
        return "redirect:/admin/categories";
    }

    @GetMapping("{categoryCode}")
    public String getUpdateForm(@PathVariable String categoryCode, Model model) {
        var categoryFormDTO = categoryService.getByCode(categoryCode);
        model.addAttribute("categoryFormDTO", categoryFormDTO);
        model.addAttribute("formIsCreate", false);
        model.addAttribute("postURL", "/admin/categories/" + categoryCode);

        return "categoryForm";
    }

    @PostMapping("{categoryCode}")
    public String updateCategory(@Valid CategoryFormDTO categoryFormDTO,
                                 BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            return "categoryForm";
        }

        categoryService.updateCategory(categoryFormDTO);
        return "redirect:/admin/categories";
    }

}
