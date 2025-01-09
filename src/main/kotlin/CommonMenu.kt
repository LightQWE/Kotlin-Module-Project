import java.util.Scanner
import java.util.InputMismatchException

abstract class CommonMenu(protected val scanner: Scanner) {

    protected var running = true

    abstract fun displayOptions()

    fun run() {
        while (running) {
            displayOptions()
            handleInput()
        }
    }

    private fun handleInput() {
        try {
            val input = scanner.nextInt()
            if (input == 0) {
                exit()
            } else {
                try {
                    onOptionSelected(input)
                } catch (e: IndexOutOfBoundsException) {
                    println("Такой опции не существует. Попробуйте еще раз.")
                }
            }
        } catch (e: InputMismatchException) {
            scanner.next()
            scanner.nextLine()
            println("Пожалуйста, введите целое число.")
            displayOptions()
        }
    }





    protected abstract fun onOptionSelected(index: Int)

    protected fun exit() {
        running = false
    }

}