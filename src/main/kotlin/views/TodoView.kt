package views

import services.ITodoService
import utils.InputUtil

class TodoView(private val todoService: ITodoService) {

    /**
     * Menampilkan view todo
     */
    fun showTodos() {
        while (true) {
            todoService.showTodos()

            println("Menu:")
            println("1. Tambah")
            println("2. Ubah")
            println("3. Cari")
            println("4. Urutkan")
            println("5. Hapus")
            println("x. Keluar")

            val input = InputUtil.input("Pilih")
            val stop = when (input) {
                "1" -> {
                    addTodo()
                    false
                }
                "2" -> {
                    updateTodo()
                    false
                }
                "3" -> {
                    searchTodo()
                    false
                }
                "4" -> {
                    sortTodo()
                    false
                }
                "5" -> {
                    removeTodo()
                    false
                }
                "x" -> true
                else -> {
                    println("[!] Pilihan tidak dimengerti.")
                    false
                }
            }

            if (stop) {
                break
            }

            println()
        }
    }

    /**
     * Menampilkan view add todo
     */
    fun addTodo() {
        println("[Menambah Todo]")
        val title = InputUtil.input("Judul (x Jika Batal)")

        if (title != "x") {
            todoService.addTodo(title)
        }
    }

    /**
     * Menampilkan view remove todo
     */
    fun removeTodo() {
        println("[Menghapus Todo]")

        val strIdTodo = InputUtil.input("[ID Todo] yang dihapus (x Jika Batal)")

        if (strIdTodo != "x") {
            val idTodo = strIdTodo.toInt()
            todoService.removeTodo(idTodo)
        }
    }

    /**
     * Menampilkan view update todo
     */
    fun updateTodo() {
        println("[Mengubah Todo]")

        val strIdTodo = InputUtil.input("ID Todo yang diubah (x Jika Batal)")

        if (strIdTodo != "x") {
            val idTodo = strIdTodo.toInt()
            val newTitle = InputUtil.input("Judul Baru")
            val strStatus = InputUtil.input("Status (1: Selesai, 0: Belum Selesai)")

            val isFinished = when (strStatus) {
                "1" -> true
                else -> false
            }

            todoService.updateTodo(idTodo, newTitle, isFinished)
        }
    }

    /**
     * Menampilkan view search todo
     */
    fun searchTodo() {
        println("[Mencari Todo]")

        val keyword = InputUtil.input("Kata Kunci (x Jika Batal)")

        if (keyword != "x") {
            val results = todoService.searchTodo(keyword)

            println("\nHasil Pencarian:")
            if (results.isEmpty()) {
                println("- Tidak ada todo yang ditemukan dengan kata kunci '$keyword'")
            } else {
                results.forEach { todo ->
                    println(todo)
                }
                println("Total: ${results.size} todo ditemukan")
            }
        }
    }

    /**
     * Menampilkan view sort todo
     */
    fun sortTodo() {
        println("[Mengurutkan Todo]")
        println("Pilih kriteria pengurutan:")
        println("1. Berdasarkan ID")
        println("2. Berdasarkan Judul (A-Z)")
        println("3. Berdasarkan Status")

        val criteria = InputUtil.input("Pilih (x Jika Batal)")

        if (criteria != "x") {
            todoService.sortTodos(criteria)
        }
    }
}