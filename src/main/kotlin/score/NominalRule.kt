package score

import Hand

class NominalRule(
    val targetNumber: Int
) : ScoringRule {

    override fun evaluate(hand: Hand): Int {
        return hand.dice.count { it.number == targetNumber } * targetNumber
    }
}