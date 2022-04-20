package br.com.vayu.controllers;

import org.springframework.cache.CacheManager;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CachingController {

    private final CacheManager cacheManager;

    public CachingController(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @GetMapping("/bGltcGEtby1jYWNoZS1kYS1hcGktYWU")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void clearCategoryApiListCache() {
        cacheManager.getCache("categoryApiList").clear();
    }

}
