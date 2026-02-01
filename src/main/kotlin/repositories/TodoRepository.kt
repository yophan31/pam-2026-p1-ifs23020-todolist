package repositories

import entities.Todo

class TodoRepository : ITodoRepository {
    val data = ArrayList<Todo>()

    override fun getAllTodos(): ArrayList<Todo> {
        return data
    }

    override fun addTodo(newTodo: Todo) {
        data.add(newTodo)
    }

    override fun removeTodo(id: Int): Boolean {
        val targetTodo = data
            .find { it.id == id }

        if (targetTodo == null) {
            return false
        }

        data.remove(targetTodo)
        return true
    }

    override fun updateTodo(id: Int, title: String, isFinished: Boolean): Boolean {
        val targetTodo = data.find { it.id == id }

        if (targetTodo == null) {
            return false
        }

        targetTodo.title = title
        targetTodo.isFinished = isFinished
        return true
    }

    override fun searchTodos(keyword: String): List<Todo> {
        return data.filter {
            it.title.contains(keyword, ignoreCase = true)
        }
    }

    override fun sortTodos(by: String) {
        when (by.lowercase()) {
            "id" -> data.sortBy { it.id }
            "title" -> data.sortBy { it.title }
            "finished" -> data.sortBy { it.isFinished }
        }
    }
}