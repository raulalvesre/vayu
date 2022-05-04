package br.com.vayu.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Profile("!test")
@RestController
@RequiredArgsConstructor
public class CachingController {

    private final CacheManager cacheManager;

    @GetMapping("/bGltcGEtby1jYWNoZS1kYS1hcGktYWU")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void clearCategoryApiListCache() {
        cacheManager.getCache("categoryApiList").clear();
    }

}
