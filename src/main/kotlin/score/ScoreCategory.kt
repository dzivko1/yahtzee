package score

enum class ScoreCategory(
    val userFriendlyName: String,
    val code: String,
    val scoringRule: ScoringRule
) {
    ONES("Ones", "ones", NominalRule(1)),
    TWOS("Twos", "twos", NominalRule(2)),
    THREES("Threes", "threes", NominalRule(3)),
    FOURS("Fours", "fours", NominalRule(4)),
    FIVES("Fives", "fives", NominalRule(5)),
    SIXES("Sixes", "sixes", NominalRule(6)),
    THREE_OF_A_KIND("Three Of A Kind", "3kind", ThreeOfAKindRule()),
    FOUR_OF_A_KIND("Four Of A Kind", "4kind", FourOfAKindRule()),
    FULL_HOUSE("Full House", "house", FullHouseRule()),
    SMALL_STRAIGHT("Small Straight", "small", SmallStraightRule()),
    LARGE_STRAIGHT("Large Straight", "large", LargeStraightRule()),
    CHANCE("Chance", "chance", ChanceRule()),
    YAHTZEE("Yahtzee", "yahtzee", YahtzeeRule());

    companion object {
        val categoriesByCode = values().associateBy { it.code }

        fun getByCode(code: String): ScoreCategory? {
            return categoriesByCode[code]
        }
    }
}