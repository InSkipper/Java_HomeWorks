package com.example.seventhhomework.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@Validated
public class Input {
    @NotBlank
    @Length(max = 50)
    private String name;

    @NotNull
    @Size(max = 5)
    private List<String> events;

    public ToDoList convertToEntity() {
        var list = new ToDoList();
        list.setName(name);
        list.setEvents(events);
        return list;
    }
}
