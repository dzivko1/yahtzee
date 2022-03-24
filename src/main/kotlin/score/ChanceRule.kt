package score

import Hand

class ChanceRule : ScoringRule {

    override fun evaluate(hand: Hand): Int {
        return hand.dice.sumOf { it.number }
    }
}