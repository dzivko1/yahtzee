package command

import com.github.ajalt.clikt.core.CliktCommand
import score.ScoreCategory

class CategoriesCommand : CliktCommand() {
    override fun run() {
        val message = ScoreCategory.values().joinToString(separator = "\n") {
            "${it.code} - ${it.userFriendlyName}"
        }
        echo(message)
    }
}