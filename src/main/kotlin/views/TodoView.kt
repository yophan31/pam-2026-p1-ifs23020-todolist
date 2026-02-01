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
                    println()
                    updateTodo()
                    false
                }
                "3" -> {
                    println()
                    searchTodo()
                    false
                }
                "4" -> {
                    println()
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
        println("[Memperbarui Todo]")

        val strIdTodo = InputUtil.input("[ID Todo] yang diubah (x Jika Batal)")

        if (strIdTodo == "x") {
            println("[x] Pembaruan todo dibatalkan.")
            return
        }

        val newTitle = InputUtil.input("Judul Baru (x Jika Batal)")

        if (newTitle == "x") {
            println("[x] Pembaruan todo dibatalkan.")
            return
        }

        val idTodo = strIdTodo.toIntOrNull()
        if (idTodo == null) {
            println("[!] ID tidak valid!")
            return
        }

        // FORMAT y/n untuk status
        val strStatus = InputUtil.input("Apakah todo sudah selesai? (y/n)")

        val newStatus = when (strStatus.lowercase()) {
            "y" -> true
            "n" -> false
            else -> {
                println("[!] Status tidak valid!")
                return
            }
        }

        todoService.updateTodo(idTodo, newTitle, newStatus)
    }

    /**
     * Menampilkan view search todo
     */
    fun searchTodo() {
        println("[Mencari Todo]")

        val keyword = InputUtil.input("Kata kunci (x Jika Batal)")

        if (keyword == "x") {
            println("[x] Pencarian todo dibatalkan.")
            return
        }

        todoService.searchTodo(keyword)
    }

    /**
     * Menampilkan view sort todo
     */
    fun sortTodo() {
        println("[Mengurutkan Todo]")

        val criteria = InputUtil.input("Urutkan berdasarkan (id/title/finished) (x Jika Batal)")

        if (criteria == "x") {
            println("[x] Pengurutan todo dibatalkan.")
            return
        }

        val ascendingInput = InputUtil.input("Urutkan secara ascending? (y/n)")
        val isAscending = ascendingInput.lowercase() == "y"

        todoService.sortTodos(criteria, isAscending)
    }
}