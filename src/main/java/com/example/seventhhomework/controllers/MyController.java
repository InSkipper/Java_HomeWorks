package com.example.seventhhomework.controllers;

import com.example.seventhhomework.dto.ToDoList;
import com.example.seventhhomework.repositories.MyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class MyController {
    private final MyRepository repo;

    @Autowired
    public MyController(MyRepository repo) {
        this.repo = repo;
    }

    @PostMapping("/post")
    public String readJSON(@RequestBody @Valid ToDoList input) {
        repo.save(input);
        return String.format("Добавлен список дел №%d c именем '%s'", input.getId(), input.getName());
    }

    @GetMapping("/get")
    public Iterable<ToDoList> getDB() {
        return repo.findAll();
    }

    @DeleteMapping("/clear")
    public String clearDB() {
        var count = repo.count();
        repo.deleteAll();
        return "Было удалено вхождений: " + count;
    }
}
