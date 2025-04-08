todos = $("#todos");

function addTodo(todo) {
	div = $(`<div class="todo" id=${todo.id}></div>`);
	div.append(`<h3> <input type='checkbox' ${todo.done ? "checked" : ""}></input> ${todo.content}</h3>`);
	div.append(`<p>id: ${todo.id}, author: ${todo.author}, created: ${todo.created}, due: ${todo.due}</p>`);
	div.append(`<button>Delete</button>`);
	todos.append(div);
	div.children("button").on("click", deleteTodo);
}

function deleteTodo() {
	$(this).parent().slideUp();
	id = $(this).parent().attr("id");
	$.ajax({url: `/todo/${id}`, method: "DELETE"});
}

$.ajax("/todo").done(function(data) {
	todos = $("#todos");
	for (todo of data) {
		addTodo(todo);
	}
});

$("#createTodo").on("submit", function(e) {
	e.preventDefault();
	content = $("#content").val();
	$.ajax({url: `/todo?content=${content}`, method: "POST"}).done(function(todo) {
		addTodo(todo);
	})
});
