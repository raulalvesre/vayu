package br.com.vayu.controllers;

import br.com.vayu.services.SubcategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/subcategories")
public class SubcategoryApiController {

    private final SubcategoryService subcategoryService;

    public SubcategoryApiController(SubcategoryService subcategoryService) {
        this.subcategoryService = subcategoryService;
    }

    @PatchMapping("deactivate/{id}")
    public ResponseEntity<?> deactive(@PathVariable int id) {
        subcategoryService.deactivate(id);
        return ResponseEntity.noContent().build();
    }

}
