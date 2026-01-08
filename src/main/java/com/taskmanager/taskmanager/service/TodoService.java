package com.taskmanager.taskmanager.service;

import static com.taskmanager.taskmanager.specification.TodoSpecification.*;

import com.taskmanager.taskmanager.dto.TodoRequestDTO;
import com.taskmanager.taskmanager.dto.TodoResponseDTO;
import com.taskmanager.taskmanager.model.Todo;
import com.taskmanager.taskmanager.repository.TodoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import  java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TodoService {
    private TodoRepository todoRepository;
    private final List<Todo> TodoList =new ArrayList<>();

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Page<TodoResponseDTO> getAll(int page, int size,String sortBy) {
        Pageable pageable  = PageRequest.of(page,size, Sort.by("createdAt").descending()) ;
        return todoRepository.findAll(pageable).map(this::mapToResponse);

        // in here we loop through the items of thetodo and change the type of the response to TodoResponseDTO
    }

    public TodoResponseDTO GetByIdTodo(Long id) {
         Todo todo = todoRepository.findById(id).orElseThrow(()-> new RuntimeException("not task found"));
        return  mapToResponse(todo);
    }
    public void DeleteByIdTodo(Long id) {
        todoRepository.deleteById(id);

    }

    public TodoResponseDTO create(TodoRequestDTO todoElement) {
        Todo todo = new Todo(todoElement.getTitle(),todoElement.getCompleted(),todoElement.getDescription());
         Todo saved =todoRepository.save(todo);
         return mapToResponse(saved);// return response of the type of the DTO
    }
    public List<TodoResponseDTO> searchByTitle(String title){
        if(title==null ||  title.isEmpty()){
            throw new IllegalArgumentException("title is null or empty");
        }
        return  todoRepository.findByTitleContainingIgnoreCase(title).stream()
                .map(this::mapToResponse)
                .toList();

    }
    public Page<TodoResponseDTO> searchTodos(
            String title,
            Boolean completed,
            LocalDateTime from,
            LocalDateTime to,
            int page,
            int size
    ) {
        Specification<Todo> spec = Specification
                .where(hasTitle(title))
                .and(isCompleted(completed))
                .and(createdAfter(from))
                .and(createdBefore(to));

        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());

        return todoRepository.findAll(spec, pageable)
                .map(this::mapToResponse);
    }
    public TodoResponseDTO update(Long id,TodoRequestDTO todoElement) {
        Todo todo= todoRepository.findById(id).orElseThrow(()-> new RuntimeException("no todo found "));
        todo.setCompleted(todoElement.getCompleted());
        todo.setDescription(todoElement.getDescription());
        todo.setTitle(todoElement.getTitle());
        Todo saved = todoRepository.save(todo);
        return mapToResponse(saved);// return response of the type of the DTO
    }


    public TodoResponseDTO mapToResponse(Todo todo){
        // this function take the todovalue id and the title ... and format a new response type Todo response
        TodoResponseDTO todoResponseDTO = new TodoResponseDTO();
        todoResponseDTO.setId(todo.getId());
        todoResponseDTO.setTitle(todo.getTitle());
        todoResponseDTO.setCompleted(todo.isCompleted());
        todoResponseDTO.setDescription(todo.getDescription());
        return  todoResponseDTO;
    }
    
}