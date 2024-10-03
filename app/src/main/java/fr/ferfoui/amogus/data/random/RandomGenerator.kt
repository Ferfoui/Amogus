package fr.ferfoui.amogus.data.random

fun generateRandomNumber(intervalMax: Int): Int {
    return (0..intervalMax).random()
}

fun generateDistinctRandomNumbers(intervalMax: Int, count: Int): List<Int> {
    val possibleNumbers = (0..intervalMax).toList()

    if (count > possibleNumbers.size) {
        throw IllegalArgumentException("Count must be less than or equal to intervalMax")
    }

    return possibleNumbers.shuffled().take(count)
}
