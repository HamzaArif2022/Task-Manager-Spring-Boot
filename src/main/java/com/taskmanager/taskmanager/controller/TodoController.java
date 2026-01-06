package com.taskmanager.taskmanager.controller;

import com.taskmanager.taskmanager.dto.TodoRequestDTO;
import com.taskmanager.taskmanager.dto.TodoResponseDTO;
import com.taskmanager.taskmanager.model.Todo;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import com.taskmanager.taskmanager.service.TodoService;

import java.awt.print.Pageable;
import java.util.List;

@RequestMapping("/todos")
@RestController
public class TodoController
{
    private TodoService todoService ;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }
//    @GetMapping
//    public List<TodoResponseDTO> getTodos() {
//        return todoService.getAll();
//    }

    @GetMapping
    public Page<TodoResponseDTO> getTodos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy) {
            return todoService.getAll(page,size,sortBy);
    }


    @PostMapping
    public TodoResponseDTO create(@Valid @RequestBody TodoRequestDTO  todo){
        return todoService.create(todo);
    }
    @PutMapping("/update/{id}")
    public TodoResponseDTO update(@PathVariable Long id,@Valid @RequestBody TodoRequestDTO  todo){
        return todoService.update(id,todo);
    }

    @GetMapping("/search")
    public List<TodoResponseDTO> GetTodoById(@RequestParam(defaultValue = "") String title) {
        return todoService.searchByTitle(title);
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