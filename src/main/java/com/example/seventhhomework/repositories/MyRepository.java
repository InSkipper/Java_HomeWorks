package com.example.seventhhomework.repositories;

import com.example.seventhhomework.dto.ToDoList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyRepository extends CrudRepository<ToDoList, Long> {
}
