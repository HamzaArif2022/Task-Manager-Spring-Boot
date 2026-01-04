package controller;

import model.Todo;
import org.springframework.web.bind.annotation.*;
import service.TodoService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/todos")
public class TodoController
{
    private TodoService todoService ;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }
    @GetMapping
    public List<Todo> getTodos() {
        return todoService.getAll();
    }

    @PostMapping("/add")
    public  Todo addTodo(@RequestBody Todo todo){
        return todoService.create(todo.getTitle());
    }
    @GetMapping("/{id}")
    public Todo GetTodoById(@PathVariable Long id){
        return todoService.GetByIdTodo(id)
                .orElseThrow(() -> new RuntimeException("Todo not found"));    }
    @DeleteMapping("/{id}")
    public  void deleteTodo(@PathVariable Long id){
         todoService.DeleteByIdTodo(id);
    }

}