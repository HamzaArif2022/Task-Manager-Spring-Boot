package com.taskmanager.taskmanager.controller;

import com.taskmanager.taskmanager.dto.TodoRequestDTO;
import com.taskmanager.taskmanager.dto.TodoResponseDTO;
import com.taskmanager.taskmanager.model.Todo;

import com.taskmanager.taskmanager.service.SchoolService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import com.taskmanager.taskmanager.service.TodoService;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.List;

@RequestMapping("/todos")
@RestController
public class TodoController
{
    private TodoService todoService ;
    @Autowired
    private SchoolService schoolService ;

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
    public List<TodoResponseDTO> Search(@RequestParam(defaultValue = "") String title) {
        return todoService.searchByTitle(title);
    }

    @GetMapping("/search")
    public Page<TodoResponseDTO> searchTodos(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Boolean completed,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime fromDate,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime toDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return todoService.searchTodos(
                title,
                completed,
                fromDate,
                toDate,
                page,
                size
        );
    }


    @GetMapping("/{id}")
    public TodoResponseDTO GetTodoById(@PathVariable Long id) {
        return todoService.GetByIdTodo(id);
    }


    @DeleteMapping("/{id}")
    public  void deleteTodo(@PathVariable Long id){
         todoService.DeleteByIdTodo(id);
    }

    @PostMapping("/enroll")
    public String enroll(
            @RequestParam Long studentId,
            @RequestParam Long subjectId
    ){
        schoolService.enrollStudent(studentId, subjectId);
        return "Student enrolled!";
    };

}