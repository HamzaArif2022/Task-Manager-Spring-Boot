package com.taskmanager.taskmanager.repository;

import com.taskmanager.taskmanager.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo,Long> {
    public List<Todo> findByTitleContainingIgnoreCase(String title);
}
