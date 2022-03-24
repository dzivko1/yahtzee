import command.LockCommand
import command.RecordCommand
import command.RollCommand
import command.UnlockCommand
import score.ScoreSet

class Player(
    val name: String
) {
    val scoreSet = ScoreSet()

    private var turnActive = false

    fun playRound(hand: Hand) {
        turnActive = true
        promptFirstRoll(hand)
        promptIntermediateAction(hand)
        if (turnActive) promptIntermediateAction(hand)
        if (turnActive) promptRecordScore(hand)
    }

    private fun promptFirstRoll(hand: Hand) {
        Input.prompt(
            "$name, type 'roll' to roll the dice."
        ) { input ->
            when (input.firstWord()) {
                "roll" -> {
                    RollCommand(hand).process(input); InputResult.Done
                }
                else -> InputResult.Invalid("Invalid command")
            }
        }
    }

    private fun promptIntermediateAction(hand: Hand) {
        Input.prompt(
            "$name, roll the dice, lock/unlock them, or record the score."
        ) { input ->
            when (input.firstWord()) {
                "roll" -> {
                    RollCommand(hand).process(input)
                    InputResult.Done
                }
                "lock" -> {
                    LockCommand(hand).process(input)
                    InputResult.Repeat
                }
                "unlock" -> {
                    UnlockCommand(hand).process(input)
                    InputResult.Repeat
                }
                "record" -> {
                    val command = RecordCommand(hand, scoreSet)
                    command.process(input)

                    if (command.didRecord) {
                        turnActive = false
                        InputResult.Done
                    }
                    else InputResult.Repeat
                }
                else -> InputResult.Invalid("Invalid command")
            }
        }
    }

    private fun promptRecordScore(hand: Hand) {
        Input.prompt(
            "$name, record the score."
        ) { input ->
            when (input.firstWord()) {
                "record" -> {
                    RecordCommand(hand, scoreSet).process(input)
                    InputResult.Done
                }
                else -> InputResult.Invalid("Invalid command")
            }
        }
    }
}