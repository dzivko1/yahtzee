package score

import Hand

class ThreeOfAKindRule : ScoringRule {

    override fun evaluate(hand: Hand): Int {
        val diceByNumber = hand.dice.groupingBy { it.number }.eachCount()

        return if (diceByNumber.containsValue(3)) {
            hand.dice.sumOf { it.number }
        } else 0
    }
}