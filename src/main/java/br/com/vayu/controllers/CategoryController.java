package br.com.vayu.controllers;

import br.com.vayu.dto.CategoryFormDTO;
import br.com.vayu.dto.CategoryPublicPageDTO;
import br.com.vayu.projections.CategoryMinifiedProjection;
import br.com.vayu.repositories.CategoryRepository;
import br.com.vayu.services.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryService categoryService, CategoryRepository categoryRepository) {
        this.categoryService = categoryService;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/category/{categoryCode}")
    public String getCategoryPublicPage(@PathVariable String categoryCode,
                                        Model model) {
        CategoryPublicPageDTO category = categoryService.getAllForCategoryPublicPage(categoryCode);

        model.addAttribute("category", category);

        return "category/categoryPage";
    }

    @GetMapping("/admin/categories")
    public String getCategories(Model model) {
        List<CategoryMinifiedProjection> categories = categoryService.getAllMinifiedInOrder();

        model.addAttribute("categories", categories);

        return "category/categoryList";
    }

    @GetMapping("/admin/categories/new")
    public String getCreateForm(CategoryFormDTO categoryFormDTO, Model model) {
        model.addAttribute("formIsCreate", true);
        model.addAttribute("postURL", "/admin/categories");

        return "category/categoryForm";
    }

    @PostMapping("/admin/categories")
    public String create(@Valid CategoryFormDTO createCategoryDTO,
                         BindingResult bindingResult,
                         Model model) {
        if (bindingResult.hasErrors()) {
            return getCreateForm(createCategoryDTO, model);
        }

        categoryService.save(createCategoryDTO);
        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/categories/{categoryCode}")
    public String getUpdateForm(@PathVariable String categoryCode, Model model) {
        CategoryFormDTO categoryFormDTO = categoryService.getByCodeAsFormDto(categoryCode);

        model.addAttribute("categoryFormDTO", categoryFormDTO);
        model.addAttribute("formIsCreate", false);
        model.addAttribute("postURL", "/admin/categories/" + categoryCode);

        return "category/categoryForm";
    }

    @PostMapping("/admin/categories/{categoryCode}")
    public String edit(@Valid CategoryFormDTO categoryFormDTO,
                       BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            return "category/categoryForm";
        }

        categoryService.update(categoryFormDTO);
        return "redirect:/admin/categories";
    }

}
