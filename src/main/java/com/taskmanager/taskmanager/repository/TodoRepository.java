package com.taskmanager.taskmanager.repository;

import com.taskmanager.taskmanager.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo,Long>, JpaSpecificationExecutor<Todo> {
    public List<Todo> findByTitleContainingIgnoreCase(String title);
}
