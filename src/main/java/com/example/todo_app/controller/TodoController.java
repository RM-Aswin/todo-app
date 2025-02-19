package com.example.todo_app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.todo_app.exception.ResourceNotFoundException;
import com.example.todo_app.model.Todo;
import com.example.todo_app.repository.TodoRepository;
import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoRepository todoRepository;

    
    public TodoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

   
    @GetMapping
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }


    @PostMapping
    public Todo createTodo(@RequestBody Todo todo) {
        return todoRepository.save(todo);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Long id, @RequestBody Todo todoDetails) {
        Todo todo = todoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id: " + id));
        todo.setTask(todoDetails.getTask());
        todo.setCompleted(todoDetails.isCompleted());
        Todo updatedTodo = todoRepository.save(todo);
        return ResponseEntity.ok(updatedTodo);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        Todo todo = todoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id: " + id));
        todoRepository.delete(todo);
        return ResponseEntity.ok().build();
    }
}
