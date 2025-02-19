package com.example.todo_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.todo_app.model.Todo;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    // Find all todos that are completed or not.
    List<Todo> findByCompleted(boolean completed);

    // Find todos whose task description contains the given keyword.
    List<Todo> findByTaskContaining(String keyword);

    // Find todos where the task exactly matches the given string.
    List<Todo> findByTask(String task);

    // Find todos where the task starts with the specified prefix.
    List<Todo> findByTaskStartingWith(String prefix);

    // Find todos that match a given task and have a specific completed status.
    List<Todo> findByTaskAndCompleted(String task, boolean completed);
}
