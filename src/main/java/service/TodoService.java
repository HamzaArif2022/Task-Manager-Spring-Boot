package service;

import model.Todo;
import org.springframework.stereotype.Service;

import  java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    private final List<Todo> TodoList =new ArrayList<>();
    private Long counter = 1L;
    public List<Todo> getAll() {
        return TodoList;
    }

    public Optional<Todo> GetByIdTodo(Long id) {
        return TodoList.stream().filter(t -> t.getId().equals(id)).findFirst();

    }
    public void DeleteByIdTodo(Long id) {
        TodoList.removeIf(t -> t.getId().equals(id));
    }

    public Todo create(String title) {
        Todo todo = new Todo(counter++, title, false);
        TodoList.add(todo);
        return todo;
    }
    
}