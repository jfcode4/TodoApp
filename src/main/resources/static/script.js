todos = document.querySelector("#todos");
fetch("http://localhost:8080/todo").then(resp => {
	return resp.json();
}).then(json => {
    console.log(json);
    for (todo of json) {
        console.log(todo);
        todos.innerHTML += "<h3>" + todo.content + "</h3>"
        todos.innerHTML += "<p>author: " + todo.author + ", created on: " + todo.creationDate + ", due: " + todo.dueDate + "</p>";
    }
});