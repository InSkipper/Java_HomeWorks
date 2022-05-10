package com.example.seventhhomework.controllers;

import com.example.seventhhomework.dto.Input;
import com.example.seventhhomework.repositories.MyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Validated
@RequiredArgsConstructor
public class MyController {
    private MyRepository repo;

    @Autowired
    public MyController(MyRepository repo) {
        this.repo = repo;
    }

    @PostMapping("/post")
    public String readJSON(@RequestBody @Valid Input input) {
        var entity = input.convertToEntity();
`        repo.save(entity);
        return String.format("Добавлен список дел №%d c именем '%s'", repo.count() + 1, input.getName());
    }

//    @GetMapping("/get")
//    public Iterable<Input> getDB() {
//        return repo.findAll();
//    }
}
