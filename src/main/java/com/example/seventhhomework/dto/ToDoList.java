package com.example.seventhhomework.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "todolists")
public class ToDoList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "todolist_name", unique = true, nullable = false)
    private String name;

    @ElementCollection
    private List<String> events;
}
