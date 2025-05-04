package fr.ferfoui.amogus.data.random

fun generateDistinctRandomNumbers(intervalMax: UInt, count: UInt): List<UInt> {
    val possibleNumbers = (1u..intervalMax).toList()

    if (count > possibleNumbers.size.toUInt()) {
        throw IllegalArgumentException("Count must be less than or equal to intervalMax")
    }

    return possibleNumbers.shuffled().take(count.toInt())
}

fun generateRandomNumbersWithDuplicates(intervalMax: UInt, count: UInt, duplicates: UInt): List<Int> {
    val possibleNumbers = (1..intervalMax.toInt()).toMutableList()

    repeat(duplicates.toInt()) {
        possibleNumbers.addAll(possibleNumbers)
    }

    if (count > possibleNumbers.size.toUInt() * duplicates) {
        throw IllegalArgumentException("Count must be less than or equal to intervalMax * duplicates")
    }

    return possibleNumbers.shuffled().take(count.toInt())
}
