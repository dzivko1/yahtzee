fun main() {
    println("Welcome to Yahtzee!")
    println("Type 'help' at any time to see a list of commands.")
    Input.prompt("Type 'start' to start the game.") { input ->
        if (input == "start") InputResult.Done
        else InputResult.Invalid("Invalid command")
    }

    val players = createPlayers()
    val dice = List(5) { Die() }
    val hand = Hand(dice)
    val game = Yahtzee(players, hand)

    Input.game = game
    game.start()
}

private fun createPlayers(): List<Player> {
    val numberOfPlayers = Input.prompt(
        "Enter the number of players."
    ) { input ->
        val number = input.toIntOrNull()
        if (number != null && number > 0) InputResult.Done
        else InputResult.Invalid("The number of players must be above 0.")
    }.toInt()

    val names = mutableListOf<String>()

    repeat(numberOfPlayers) { i ->
        val name = Input.prompt(
            "Enter the name for player ${i + 1}."
        ) { input ->
            if (input !in names) InputResult.Done
            else InputResult.Invalid("Player names must be unique.")
        }
        names += name
    }

    return names.map { Player(it) }
}
