package br.com.vayu.controllers;

import br.com.vayu.dto.CompleteCategoryDTO;
import br.com.vayu.services.CategoryService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping(produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<List<CompleteCategoryDTO>> getCompleteCategoryList() {
        return ResponseEntity.ok(categoryService.getCompleteActiveCategoryDtoList());
    }

}
