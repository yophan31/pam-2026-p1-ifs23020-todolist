package services

import entities.Todo
import repositories.ITodoRepository

class TodoService(private val todoRepository: ITodoRepository) : ITodoService {
    override fun showTodos() {
        val todos = todoRepository.getAllTodos()

        println("Daftar Todo:")
        var counter = 0
        for (todo in todos) {
            counter++
            println(todo)
        }

        if (counter <= 0) {
            println("- Data todo belum tersedia!")
        }
    }

    override fun addTodo(title: String) {
        val newTodo = Todo(title = title)
        todoRepository.addTodo(newTodo)
    }

    override fun removeTodo(id: Int) {
        val success = todoRepository.removeTodo(id)
        if (!success) {
            println("[!] Gagal menghapus todo dengan ID: $id.")
            return
        }
    }

    // Implementasi update todo
    override fun updateTodo(id: Int, newTitle: String, isFinished: Boolean) {
        val success = todoRepository.updateTodo(id, newTitle, isFinished)
        if (!success) {
            println("[!] Gagal mengupdate todo dengan ID: $id.")
        } else {
            println("[✓] Todo berhasil diupdate!")
        }
    }

    // Implementasi search todo
    override fun searchTodo(keyword: String): List<Todo> {
        val todos = todoRepository.getAllTodos()
        return todos.filter {
            it.title.contains(keyword, ignoreCase = true)
        }
    }

    // Implementasi sort todo
    override fun sortTodos(criteria: String) {
        val todos = todoRepository.getAllTodos()

        val sortedTodos = when (criteria) {
            "1" -> {
                todos.sortedBy { it.id }
            }
            "2" -> {
                todos.sortedBy { it.title.lowercase() }
            }
            "3" -> {
                todos.sortedBy { it.isFinished }
            }
            else -> {
                println("[!] Kriteria pengurutan tidak valid.")
                return
            }
        }

        todoRepository.setAllTodos(sortedTodos)
        println("[✓] Todo berhasil diurutkan!")
    }
}
