import com.github.ajalt.clikt.core.CliktError
import com.github.ajalt.clikt.core.PrintHelpMessage
import com.github.ajalt.clikt.core.PrintMessage
import com.github.ajalt.clikt.core.UsageError
import command.CategoriesCommand
import command.HelpCommand
import command.ScoreCommand

sealed interface InputResult {
    object Done : InputResult
    object Repeat : InputResult
    data class Invalid(val message: String? = null) : InputResult
}

object Input {
    var game: Yahtzee? = null

    fun prompt(
        promptMessage: String,
        inputProcessor: (String) -> InputResult
    ): String {
        var input: String
        var result: InputResult

        do {
            println(promptMessage)

            input = readCommand()

            result = try {
                inputProcessor(input)
            } catch (e: PrintHelpMessage) {
                println(e.command.getFormattedHelp())
                InputResult.Invalid()
            } catch (e: PrintMessage) {
                println(e.message)
                InputResult.Invalid()
            } catch (e: UsageError) {
                printlnErr(e.helpMessage())
                InputResult.Invalid()
            } catch (e: CliktError) {
                printlnErr(e.message)
                InputResult.Invalid()
            }

            if (result is InputResult.Invalid && result.message != null) {
                printlnErr(result.message)
            }

        } while (result !is InputResult.Done)

        return input
    }

    private fun readCommand(): String {
        var input: String

        do {
            input = readln()
            val caught = interceptCommand(input)
        } while (caught)

        return input
    }

    private fun interceptCommand(input: String): Boolean {
        return try {
            when (input.firstWord()) {
                "help" -> HelpCommand().process(input)
                "score" -> {
                    game?.let {
                        ScoreCommand(it).process(input)
                    } ?: println("The game has not started yet.")
                }
                "categories" -> CategoriesCommand().process(input)
                else -> return false
            }
            true

        } catch (e: PrintHelpMessage) {
            println(e.command.getFormattedHelp())
            true
        } catch (e: PrintMessage) {
            println(e.message)
            true
        } catch (e: UsageError) {
            printlnErr(e.helpMessage())
            true
        } catch (e: CliktError) {
            printlnErr(e.message)
            true
        }
    }
}