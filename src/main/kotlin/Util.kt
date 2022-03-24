import com.github.ajalt.clikt.core.CliktCommand

fun CliktCommand.process(inputString: String) {
    val parts = inputString.split(' ')
    parse(parts.drop(1))
}

fun printlnErr(message: Any?) {
    System.err.println(message)
}

fun <T, R : Comparable<R>> List<T>.maxBy(selector: (T) -> R): T {
    if (isEmpty()) throw NoSuchElementException()
    return maxByOrNull(selector)!!
}

fun String.firstWord() = substringBefore(' ')