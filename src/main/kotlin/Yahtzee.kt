import score.ScoreCategory

class Yahtzee(
    val players: List<Player>,
    val hand: Hand
) {
    init {
        require(players.isNotEmpty())
    }

    fun start() {
        println("Game started!")

        repeat(13) {
            println("=== Round ${it + 1} ===")
            runRound()
        }

        declareWinner()
    }

    private fun runRound() {
        players.forEach {
            it.playRound(hand)
            hand.reset()
        }
    }

    private fun declareWinner() {
        println()
        printScoreboard()

        val winner = players.maxBy { it.scoreSet.total }
        val tiedWinners = players.filter { it.scoreSet == winner.scoreSet }

        if (tiedWinners.count() == 1) {
            println("Game over! The winner is ${winner.name}.")
        } else {
            val winnersString = tiedWinners.joinToString { it.name }
            println("Game over! The game is tied between $winnersString")
        }
    }

    fun printScoreboard() {
        val scoreString = buildString {
            append("\t")
            appendLine(players.joinToString(separator = ",  ") { it.name })

            ScoreCategory.values().forEach { category ->
                appendScoreRow(category.userFriendlyName, players.map { it.scoreSet[category] })

                if (category == ScoreCategory.SIXES) {
                    appendScoreRow("> Sum", players.map { it.scoreSet.upperTotal })
                    appendScoreRow("> Bonus", players.map { it.scoreSet.bonus })
                    appendLine()
                }
            }

            appendScoreRow("> TOTAL", players.map { it.scoreSet.total })
        }
        println(scoreString)
    }

    private fun StringBuilder.appendScoreRow(title: String, values: List<Int?>) {
        val format = buildString {
            append("%-16s  ")
            values.forEach {
                append("%-5s")
            }
        }
        val formatArgs = values.map { it?.toString() ?: "-" }.toTypedArray()
        append(format.format(title, *formatArgs))
        appendLine()
    }
}