package repositories

import entities.Todo

interface ITodoRepository {
    fun getAllTodos(): List<Todo>
    fun addTodo(newTodo: Todo)
    fun removeTodo(id: Int): Boolean
    fun updateTodo(id: Int, newTitle: String, isFinished: Boolean): Boolean
    fun findTodoById(id: Int): Todo?
    fun setAllTodos(todos: List<Todo>)  // Tambahkan method ini
}
