package score

import Hand

class FourOfAKindRule : ScoringRule {

    override fun evaluate(hand: Hand): Int {
        val diceByNumber = hand.dice.groupingBy { it.number }.eachCount()

        return if (diceByNumber.containsValue(4)) {
            hand.dice.sumOf { it.number }
        } else 0
    }
}