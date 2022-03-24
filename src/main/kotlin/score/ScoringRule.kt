package score

import Hand

interface ScoringRule {
    fun evaluate(hand: Hand): Int
}