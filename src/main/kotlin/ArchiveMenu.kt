import java.util.Scanner
class ArchiveMenu : CommonMenu(Scanner(System.`in`)) {

    private val archives = mutableListOf<Archive>()

    override fun displayOptions() {
        println("\nСписок архивов:")
        println("0. Выход")
        println("1. Добавить новый архив")

        for ((index, archive) in archives.withIndex()) {
            println("${index + 2}. $archive")
        }

        println("Введите номер опции:")
    }

    override fun onOptionSelected(index: Int) {
        when (index) {
            1 -> addNewArchive()
            else -> viewArchiveNotes(index - 2)
        }
    }

    private fun addNewArchive() {
        print("Введите имя нового архива: ")
        val name = readNonEmptyString("Имя архива не может быть пустым!")
        archives.add(Archive(name, mutableListOf()))
        println("Архив '$name' добавлен.")
    }

    private fun viewArchiveNotes(index: Int) {
        val selectedArchive = archives[index]
        NoteMenu(selectedArchive.notes, this::exit).run()
    }



    private fun readNonEmptyString(errorMessage: String): String {
        var input: String
        do {
            input = scanner.nextLine().trim()
            if (input.isBlank()) {
                println(errorMessage)
            }
        } while (input.isBlank())
        return input
    }
}