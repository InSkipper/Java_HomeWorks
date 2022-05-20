package com.example.defaultproject.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
    @GetMapping("/public/api")
    public String getPublicApi() {
        return "Public";
    }

    @GetMapping("/admin/api")
    public String getAdminApi(Authentication auth) {
        return getNameAndRoles(auth);
    }

    @GetMapping("/support/api")
    public String getSupportApi(Authentication auth) {
        return getNameAndRoles(auth);
    }

    private String getNameAndRoles(Authentication auth) {
        return String.format("Пользователь '%s' имеет роли: %s", auth.getName(), auth.getAuthorities());
    }
}
