package br.com.vayu.controllers;

import br.com.vayu.dto.CategoryApiDTO;
import br.com.vayu.services.CategoryService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<CategoryApiDTO>> getCompleteCategoryList() {
        return ResponseEntity.ok(categoryService.getAllActiveCategoryAsApiDtoList());
    }

    @PatchMapping("deactivate/{id}")
    public ResponseEntity<?> deactivate(@PathVariable int id) {
        categoryService.deactivate(id);
        return ResponseEntity.noContent().build();
    }

}
