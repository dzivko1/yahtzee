package score

import Hand

class YahtzeeRule : ScoringRule {

    override fun evaluate(hand: Hand): Int {
        hand.dice.iterator().run {
            val theNumber = next().number
            var broken = false

            forEachRemaining {
                if (it.number != theNumber) {
                    broken = true
                    return@forEachRemaining
                }
            }

            return if (broken) 0 else 50
        }
    }
}