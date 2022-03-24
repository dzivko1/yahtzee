package score

import Hand

class LargeStraightRule : ScoringRule {

    override fun evaluate(hand: Hand): Int {
        val cleanedDice = hand.dice
            .distinctBy { it.number }
            .sortedBy { it.number }

        var streak = 1

        cleanedDice.iterator().run {
            var lastNumber = next().number

            forEachRemaining {
                if (it.number == lastNumber + 1) {
                    streak++
                } else {
                    streak = 1
                }
                lastNumber = it.number
            }
        }

        return if (streak == 5) 40 else 0
    }
}