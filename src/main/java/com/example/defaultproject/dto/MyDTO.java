package com.example.defaultproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyDTO {
    double price;
    Info info;

    public MyDTO setId(int id) {
        return new MyDTO(
                price,
                new Info(id, info.getDate()));
    }
}
