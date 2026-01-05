package com.taskmanager.taskmanager.repository;

import com.taskmanager.taskmanager.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo,Long> {

}
