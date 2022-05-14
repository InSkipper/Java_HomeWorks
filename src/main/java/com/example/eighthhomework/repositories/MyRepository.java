package com.example.eighthhomework.repositories;

import com.example.eighthhomework.dto.ToDoList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyRepository extends CrudRepository<ToDoList, Long> {
}
