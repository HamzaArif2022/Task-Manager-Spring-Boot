package com.taskmanager.taskmanager.controller;

import com.taskmanager.taskmanager.dto.TodoRequestDTO;
import com.taskmanager.taskmanager.dto.TodoResponseDTO;
import com.taskmanager.taskmanager.model.Todo;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.taskmanager.taskmanager.service.TodoService;

import java.util.List;

@RequestMapping("/todos")
@RestController
public class TodoController
{
    private TodoService todoService ;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }
    @GetMapping
    public List<TodoResponseDTO> getTodos() {
        return todoService.getAll();
    }

    @PostMapping
    public TodoResponseDTO create(@Valid @RequestBody TodoRequestDTO  todo){
        return todoService.create(todo);
    }
    @PutMapping("/update/{id}")
    public TodoResponseDTO update(@PathVariable Long id,@Valid @RequestBody TodoRequestDTO  todo){
        return todoService.update(id,todo);
    }
    @GetMapping("/{id}")
    public TodoResponseDTO GetTodoById(@PathVariable Long id) {
        return todoService.GetByIdTodo(id);
    }

    @DeleteMapping("/{id}")
    public  void deleteTodo(@PathVariable Long id){
         todoService.DeleteByIdTodo(id);
    }

}