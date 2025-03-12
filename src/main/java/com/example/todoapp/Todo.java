package com.example.todoapp;

public class Todo {
	public final int id;
	public final String author;
	public final String created;
	public final String due;
	public final String content;
	public final boolean done;

	public Todo(int id, String author, String created, String due, String content, boolean done) {
		this.id = id;
		this.author = author;
		this.created = created;
		this.due = due;
		this.content = content;
		this.done = done;
	}

}
