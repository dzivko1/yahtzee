package command

import Yahtzee
import com.github.ajalt.clikt.core.CliktCommand

class ScoreCommand(
    private val game: Yahtzee
) : CliktCommand() {

    override fun run() {
        game.printScoreboard()
    }
}