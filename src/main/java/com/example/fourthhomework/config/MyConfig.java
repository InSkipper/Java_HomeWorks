package com.example.fourthhomework.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties("my")
@RequiredArgsConstructor
@Getter
public class MyConfig {
    private final List<String> list = new ArrayList<>();

    @Setter
    private String envVariable;
}
