package services

interface ITodoService {
    fun showTodos()
    fun addTodo(title: String)
    fun removeTodo(id: Int)
    fun updateTodo(id: Int, newTitle: String, isFinished: Boolean)  // Tambahkan
    fun searchTodo(keyword: String): List<entities.Todo>  // Tambahkan
    fun sortTodos(criteria: String)  // Tambahkan
}
