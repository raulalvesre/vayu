package br.com.vayu.controllers;

import br.com.vayu.dto.CategoryApiDTO;
import br.com.vayu.services.CategoryService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryApiController {

    private final CategoryService categoryService;

    public CategoryApiController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @Cacheable("categoryApiList")
    public List<CategoryApiDTO> getCategoryApiDtoList() {
        return categoryService.getAllForCategoryApi();
    }

    @PatchMapping("deactivate/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deactivate(@PathVariable int id) {
        categoryService.deactivate(id);
    }

}
