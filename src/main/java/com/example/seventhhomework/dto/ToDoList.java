package com.example.seventhhomework.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "todolists")
public class ToDoList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    @Length(max = 50)
    @NotBlank
    private String name;

    @ElementCollection
    @Column(name = "events")
    @Size(min = 1, max = 10)
    @NotNull
    private List<@NotBlank @Length(max = 50) String> events;
}
