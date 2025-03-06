package com.example.todoapp;

public class Todo {
	static int nextId = 0;
	public int id;
	public String author;
	public String creationDate;
	public String dueDate;
	public String content;

	public Todo(String author, String creationDate, String dueDate, String content) {
		id = nextId++;
		this.author = author;
		this.creationDate = creationDate;
		this.dueDate = dueDate;
		this.content = content;
	}

}
