import java.util.Scanner
class NoteMenu(
    private val notes: MutableList<Note>,
    private val backAction: () -> Unit
) : CommonMenu(Scanner(System.`in`)) {

    override fun displayOptions() {
        println("\nСписок заметок:")
        println("0. Назад")
        println("1. Добавить новую заметку")

        for ((index, note) in notes.withIndex()) {
            println("${index + 2}. ${note.title}")
        }

        println("Введите номер опции:")
    }

    override fun onOptionSelected(index: Int) {
        when (index) {
            1 -> addNewNote()
            else -> viewNote(notes[index - 2])
        }
    }

    private fun addNewNote() {
        print("Введите заголовок новой заметки: ")
        val title = readNonEmptyString("Заголовок заметки не может быть пустым!")
        print("Введите содержание заметки: ")
        val content = readNonEmptyString("Содержание заметки не может быть пустым!")
        notes.add(Note(title, content))
        println("Заметка '$title' добавлена.")
    }

    private fun viewNote(note: Note) {
        println("\nЗаметка:\n${note.toString()}\n")
        pressAnyKeyToContinue()
    }

    private fun pressAnyKeyToContinue() {
        println("Нажмите любую клавишу для продолжения...")
        scanner.nextLine()
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