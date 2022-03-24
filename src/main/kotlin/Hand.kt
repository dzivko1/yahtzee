class Hand(
    val dice: List<Die>
) {
    private val lockList = MutableList(5) { false }

    fun isDieLocked(dieIndex: Int): Boolean = lockList[dieIndex]

    fun lock(dieIndex: Int) {
        lockList[dieIndex] = true
    }

    fun unlock(dieIndex: Int) {
        lockList[dieIndex] = false
    }

    fun reset() {
        lockList.fill(false)
    }

    fun roll() {
        dice.forEachIndexed { index, die ->
            if (!isDieLocked(index)) {
                die.roll()
            }
        }
    }

    override fun toString(): String {
        return buildString {
            dice.forEachIndexed { index, die ->
                if (isDieLocked(index))
                    append("L")

                append("${die.number}   ")
            }
        }
    }
}