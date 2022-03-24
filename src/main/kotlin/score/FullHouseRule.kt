package score

import Hand

class FullHouseRule : ScoringRule {

    override fun evaluate(hand: Hand): Int {
        val diceByNumber = hand.dice.groupingBy { it.number }.eachCount()

        return if (
            diceByNumber.containsValue(3) &&
            diceByNumber.containsValue(2)
        ) 25 else 0
    }
}