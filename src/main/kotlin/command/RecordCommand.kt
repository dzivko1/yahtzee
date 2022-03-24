package command

import Hand
import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.arguments.check
import score.ScoreCategory
import score.ScoreSet
import kotlin.properties.Delegates

class RecordCommand(
    private val hand: Hand,
    private val scoreSet: ScoreSet
) : CliktCommand() {
    private val categoryCode by argument().check {
        it in ScoreCategory.categoriesByCode.keys
    }

    var didRecord by Delegates.notNull<Boolean>()
        private set

    override fun run() {
        val category = ScoreCategory.getByCode(categoryCode)!!

        didRecord = if (scoreSet[category] == null) {
            val score = category.scoringRule.evaluate(hand)
            scoreSet.record(category, score)

            echo("Recorded $score points to ${category.userFriendlyName}.")
            true
        } else {
            echo("The category ${category.userFriendlyName} is already filled.")
            false
        }

    }
}