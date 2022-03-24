package command

import Hand
import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.arguments.convert
import com.github.ajalt.clikt.parameters.arguments.multiple
import com.github.ajalt.clikt.parameters.types.int
import com.github.ajalt.clikt.parameters.types.restrictTo

class UnlockCommand(
    private val hand: Hand
) : CliktCommand() {
    private val dieIndices by argument()
        .int().restrictTo(1, 5)
        .convert { it - 1 }
        .multiple()

    override fun run() {
        dieIndices.forEach {
            hand.unlock(it)
        }
        echo(hand)
    }
}