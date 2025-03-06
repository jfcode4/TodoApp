package com.example.todoapp;

import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

class Database {
	List<Todo> todos;
	public Database() {
		try (
			Connection conn = DriverManager.getConnection("jdbc:sqlite:todos.db");
			Statement s = conn.createStatement()
		) {
			s.executeUpdate("create table if not exists todos(author string, content string)");
		} catch (Exception e) {
			e.printStackTrace();
		}
		//conn.prepareCall("create table todos(author string, content string)");
		todos = new ArrayList<>();
		todos.add(new Todo("jacob", "2025-03-02", "2025-04-04", "Do some stuff"));
		todos.add(new Todo("jacob", "2025-03-05", "2025-04-05", "Do other thing"));
		todos.add(new Todo("reuven", "2025-04-05", "2025-05-05", "Do nothing"));
		todos.add(new Todo("shimon", "2025-05-05", "2025-06-05", "Do everything"));
		deleteTodo(1);
	}

	public List<Todo> allTodos() {
		return todos;
	}

	public Todo firstTodo() {
		return todos.get(0);
	}
	public Todo getTodo(int id) {
		for (Todo todo : todos) {
			if (todo.id == id) {
				return todo;
			}
		}
		return null;
	}

	public void createTodo(com.example.todoapp.Todo todo) {
		todos.add(todo);
	}

	public void deleteTodo(int id) {
		todos.removeIf(t -> t.id == id);
	}

	public void updateTodo(int id, String author, String creationDate, String dueDate, String content) {
		for (Todo todo : todos) {
			if (todo.id == id) {
				if (author != null) todo.author = author;
				if (creationDate != null) todo.creationDate = creationDate;
				if (dueDate != null) todo.dueDate = dueDate;
				if (content != null) todo.content = content;
			}
		}
	}
}
