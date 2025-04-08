package com.example.todoapp;

import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.lang.RuntimeException;

class Database {
	List<Todo> todos;
	Connection conn;

	public Database() {
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:todos.db");
			Statement s = conn.createStatement();
			s.executeUpdate("create table if not exists todos(author text, created text, due text, content text, done bool)");
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Could not connect to database", e);
		}
	}

	public List<Todo> allTodos() {
		List<Todo> result = new ArrayList<Todo>();
		try {
			Statement s = conn.createStatement();
			ResultSet r = s.executeQuery("SELECT rowid, * FROM todos");
			while (r.next()) {
				Todo todo = new Todo(
					r.getInt("rowid"),
					r.getString("author"),
					r.getString("created"),
					r.getString("due"),
					r.getString("content"),
					r.getBoolean("done")
				);
				result.add(todo);
			}
			s.close();
		} catch (SQLException e) {
			throw new RuntimeException("SQL Error", e);
		}
		return result;
	}

	public Todo firstTodo() {
		try {
			Statement s = conn.createStatement();
			ResultSet r = s.executeQuery("SELECT rowid, * FROM todos LIMIT 1");
			s.close();
			if (r.next()) {
				return new Todo(
					r.getInt("rowid"),
					r.getString("author"),
					r.getString("created"),
					r.getString("due"),
					r.getString("content"),
					r.getBoolean("done")
				);
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new RuntimeException("SQL Error", e);
		}
	}

	public Todo getTodo(int id) {
		try {
			Statement s = conn.createStatement();
			ResultSet r = s.executeQuery("SELECT rowid, * FROM todos WHERE rowid=" + id);
			if (r.next()) {
				Todo res = new Todo(
					r.getInt("rowid"),
					r.getString("author"),
					r.getString("created"),
					r.getString("due"),
					r.getString("content"),
					r.getBoolean("done")
				);
				s.close();
				return res;
			} else {
				s.close();
				return null;
			}
		} catch (SQLException e) {
			throw new RuntimeException("SQL Error", e);
		}
	}

	public Todo createTodo(String author, String due, String content) {
		try {
			PreparedStatement s = conn.prepareStatement("INSERT INTO todos(author, created, due, content) VALUES (?, datetime('now'), ?, ?) RETURNING rowid,*");
			s.setString(1, author);
			s.setString(2, due);
			s.setString(3, content);
			ResultSet r = s.executeQuery();
			if (r.next()) {
				Todo res = new Todo(
					r.getInt("rowid"),
					r.getString("author"),
					r.getString("created"),
					r.getString("due"),
					r.getString("content"),
					r.getBoolean("done")
				);
				s.close();
				return res;
			}
			s.close();
		} catch (SQLException e) {
			throw new RuntimeException("SQL Error", e);
		}
		return null;
	}

	public void deleteTodo(int id) {
		try {
			PreparedStatement s = conn.prepareStatement("DELETE FROM todos WHERE rowid=?");
			s.setInt(1, id);
			s.executeUpdate();
			s.close();
		} catch (SQLException e) {
			throw new RuntimeException("SQL Error", e);
		}
	}

	public void updateTodoAuthor(int id, String author) {
		try {
			PreparedStatement s = conn.prepareStatement("UPDATE todos SET author=? WHERE rowid=?");
			s.setString(1, author);
			s.setInt(2, id);
			s.executeUpdate();
			s.close();
		} catch (SQLException e) {
			throw new RuntimeException("SQL Error", e);
		}
	}
	public void updateTodoCreated(int id, String created) {
		try {
			PreparedStatement s = conn.prepareStatement("UPDATE todos SET created=? WHERE rowid=?");
			s.setString(1, created);
			s.setInt(2, id);
			s.executeUpdate();
			s.close();
		} catch (SQLException e) {
			throw new RuntimeException("SQL Error", e);
		}
	}
	public void updateTodoDue(int id, String due) {
		try {
			PreparedStatement s = conn.prepareStatement("UPDATE todos SET due=? WHERE rowid=?");
			s.setString(1, due);
			s.setInt(2, id);
			s.executeUpdate();
			s.close();
		} catch (SQLException e) {
			throw new RuntimeException("SQL Error", e);
		}
	}
	public void updateTodoContent(int id, String content) {
		try {
			PreparedStatement s = conn.prepareStatement("UPDATE todos SET content=? WHERE rowid=?");
			s.setString(1, content);
			s.setInt(2, id);
			s.executeUpdate();
			s.close();
		} catch (SQLException e) {
			throw new RuntimeException("SQL Error", e);
		}
	}
	public void updateTodoDone(int id, boolean done) {
		try {
			PreparedStatement s = conn.prepareStatement("UPDATE todos SET done=? WHERE rowid=?");
			s.setBoolean(1, done);
			s.setInt(2, id);
			s.executeUpdate();
			s.close();
		} catch (SQLException e) {
			throw new RuntimeException("SQL Error", e);
		}
	}
}
