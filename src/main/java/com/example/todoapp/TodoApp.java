package com.example.todoapp;

import java.util.List;
import java.lang.RuntimeException;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

@SpringBootApplication
@RestController
public class TodoApp {
	Database db;
	public TodoApp() {
		db = new Database();
	}

	@GetMapping("/todo")
	List<Todo> allTodos() {
		return db.allTodos();
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	public static class TodoNotFoundException extends RuntimeException {}

	@GetMapping("/todo/{id}")
	Todo getTodo(@PathVariable int id) throws TodoNotFoundException {
		Todo todo = db.getTodo(id);
		if (todo == null) throw new TodoNotFoundException();
		return todo;
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/todo/{id}")
	void deleteTodo(@PathVariable int id) throws TodoNotFoundException {
		Todo todo = db.getTodo(id);
		if (todo == null) throw new TodoNotFoundException();
		db.deleteTodo(id);
	}
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PutMapping("/todo/{id}")
	void updateTodo(@PathVariable int id, @RequestParam Map<String, String> map) {
		db.updateTodo(id, map.get("author"), map.get("creationDate"), map.get("dueDate"), map.get("content"));
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/todo")
	void createTodo() {
		db.createTodo(new Todo("test", "test", "test", "test"));
	}

	public static void main(String[] args) {
		SpringApplication.run(TodoApp.class, args);
	}
}
