todos = document.querySelector("#todos");
fetch("http://localhost:8080/todo").then(resp => {
	return resp.json();
}).then(json => {
    for (todo of json) {
        todos.innerHTML +=`<h3> <input type='checkbox' ${todo.done ? "checked" : ""}></input>${todo.content}</h3>
		<p>id: ${todo.id}, author: ${todo.author}, created on: ${todo.created}, due: ${todo.due}</p>`;
    }
});
