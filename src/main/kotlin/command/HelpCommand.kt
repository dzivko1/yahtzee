package command

import com.github.ajalt.clikt.core.CliktCommand

class HelpCommand : CliktCommand() {
    override fun run() {
        echo(
            """
                score - show the scoreboard
                roll - roll the unlocked dice
                lock <die_index...> - lock dice specified by index (1 to 5)
                unlock <die_index...> - unlock dice specified by index
                record <category_code> - record score into a category
                categories - shows a list of all category codes
            """.trimIndent()
        )
    }
}