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
        val targetTodo = data.find { it.id == id }

        if (targetTodo == null) {
            return false
        }

        data.remove(targetTodo)
        return true
    }

    // Method baru untuk update
    override fun updateTodo(id: Int, newTitle: String, isFinished: Boolean): Boolean {
        val targetTodo = data.find { it.id == id }

        if (targetTodo == null) {
            return false
        }

        targetTodo.title = newTitle
        targetTodo.isFinished = isFinished
        return true
    }

    // Method baru untuk find by ID
    override fun findTodoById(id: Int): Todo? {
        return data.find { it.id == id }
    }

    override fun setAllTodos(todos: List<Todo>) {
        data.clear()
        data.addAll(todos)
    }
}
