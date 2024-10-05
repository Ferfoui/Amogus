package fr.ferfoui.amogus.data.random

fun generateDistinctRandomNumbers(intervalMax: Int, count: Int): List<Int> {
    val possibleNumbers = (1..intervalMax).toList()

    if (count > possibleNumbers.size) {
        throw IllegalArgumentException("Count must be less than or equal to intervalMax")
    }

    return possibleNumbers.shuffled().take(count)
}
