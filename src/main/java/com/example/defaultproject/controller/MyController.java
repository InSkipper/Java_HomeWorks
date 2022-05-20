package com.example.defaultproject.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

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
        var name = auth.getName();
        var roles = auth.getAuthorities().stream()
                .map(role -> role.getAuthority().split("_")[1])
                .collect(Collectors.toList());
        return String.format("Пользователь '%s' имеет роли: %s", name, roles);
    }
}
