import kotlin.random.Random

class Die {
    var number = 1
        private set

    fun roll() {
        number = Random.nextInt(1, 7)
    }
}