package br.com.vayu.controllers;

import br.com.vayu.services.SubcategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subcategories")
public class SubcategoryApiController {

    private final SubcategoryService subcategoryService;

    public SubcategoryApiController(SubcategoryService subcategoryService) {
        this.subcategoryService = subcategoryService;
    }

    @PatchMapping("deactivate/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deactive(@PathVariable int id) {
        subcategoryService.deactivate(id);
    }

}
