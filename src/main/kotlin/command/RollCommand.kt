package command

import Hand
import com.github.ajalt.clikt.core.CliktCommand

class RollCommand(
    private val hand: Hand
) : CliktCommand() {

    override fun run() {
        hand.roll()
        echo(hand)
    }
}