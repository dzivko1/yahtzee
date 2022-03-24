package score

class ScoreSet {
    private val scoreMap = mutableMapOf<ScoreCategory, Int>()

    val upperTotal: Int
        get() = getOrZero(ScoreCategory.ONES) +
                getOrZero(ScoreCategory.TWOS) +
                getOrZero(ScoreCategory.THREES) +
                getOrZero(ScoreCategory.FOURS) +
                getOrZero(ScoreCategory.FIVES) +
                getOrZero(ScoreCategory.SIXES)

    val lowerTotal: Int
        get() = getOrZero(ScoreCategory.THREE_OF_A_KIND) +
                getOrZero(ScoreCategory.FOUR_OF_A_KIND) +
                getOrZero(ScoreCategory.FULL_HOUSE) +
                getOrZero(ScoreCategory.SMALL_STRAIGHT) +
                getOrZero(ScoreCategory.LARGE_STRAIGHT) +
                getOrZero(ScoreCategory.CHANCE) +
                getOrZero(ScoreCategory.YAHTZEE)

    val bonus: Int
        get() = if (upperTotal >= 63) 35 else 0

    val total: Int
        get() = upperTotal + lowerTotal + bonus

    operator fun get(category: ScoreCategory): Int? {
        return scoreMap[category]
    }

    fun record(category: ScoreCategory, score: Int) {
        scoreMap[category] = score
    }

    private fun getOrZero(category: ScoreCategory): Int {
        return scoreMap[category] ?: 0
    }
}