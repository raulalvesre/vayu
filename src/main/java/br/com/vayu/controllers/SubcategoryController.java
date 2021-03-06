package br.com.vayu.controllers;

import br.com.vayu.dto.SubcategoryFormDTO;
import br.com.vayu.projections.SubcategoryMinifiedProjection;
import br.com.vayu.services.CategoryService;
import br.com.vayu.services.SubcategoryService;
import br.com.vayu.validation.SubcategoryFormDTOValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin/subcategories")
@RequiredArgsConstructor
public class SubcategoryController {

    private final SubcategoryService subcategoryService;
    private final CategoryService categoryService;
    private final SubcategoryFormDTOValidator subcategoryFormDTOValidator;

    @InitBinder("subcategoryFormDTO")
    void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(subcategoryFormDTOValidator);
    }

    @GetMapping("{categoryCode}")
    public String getSubcategoriesByCategoryCode(@PathVariable String categoryCode, Model model) {
        List<SubcategoryMinifiedProjection> subcategories =
                subcategoryService.getAllMinifiedByCategoryCode(categoryCode);

        model.addAttribute("subcategories", subcategories);
        model.addAttribute("categoryDto", categoryService.getByCodeMinified(categoryCode));
        return "subcategory/subcategoriesList";
    }

    @GetMapping("/new")
    public String getCreateForm(SubcategoryFormDTO subcategoryFormDTO, Model model) {
        model.addAttribute("formIsCreate", true);
        model.addAttribute("categories", categoryService.getAllMinifiedInOrder());
        model.addAttribute("postURL", "/admin/subcategories");

        return "subcategory/subcategoryForm";
    }

    @PostMapping
    public String create(@Valid SubcategoryFormDTO subcategoryFormDTO,
                         BindingResult bindingResult,
                         Model model) {
        if (bindingResult.hasErrors()) {
            return getCreateForm(subcategoryFormDTO, model);
        }

        subcategoryService.save(subcategoryFormDTO);
        return "redirect:/admin/subcategories/" + subcategoryFormDTO.getCategoryCode();
    }

    @GetMapping("{categoryCode}/{subcategoryCode}")
    public String getUpdateForm(@PathVariable String categoryCode,
                                @PathVariable String subcategoryCode,
                                Model model) {
        SubcategoryFormDTO subcategoryFormDTO =
                subcategoryService.getByCodeAndCategoryCodeAsFormDto(categoryCode, subcategoryCode);

        model.addAttribute("subcategoryFormDTO", subcategoryFormDTO);
        model.addAttribute("categories", categoryService.getAllMinifiedInOrder());
        model.addAttribute("postURL", "/admin/subcategories/" + categoryCode + "/" + subcategoryCode);
        model.addAttribute("formIsCreate", false);

        return "subcategory/subcategoryForm";
    }

    @PostMapping("{categoryCode}/{subcategoryCode}")
    public String edit(@PathVariable String categoryCode,
                       @PathVariable String subcategoryCode,
                       @Valid SubcategoryFormDTO subcategoryFormDTO,
                       BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            return "subcategory/subcategoryForm";
        }

        subcategoryService.update(subcategoryFormDTO);
        return "redirect:/admin/subcategories/" + subcategoryFormDTO.getCategoryCode();
    }

}
