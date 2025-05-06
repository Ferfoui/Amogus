package fr.ferfoui.amogus.data.random

fun generateDistinctRandomNumbers(intervalMax: UInt, count: UInt): List<UInt> =
    (1u..intervalMax).toList()
        .shuffled()
        .take(count.toInt())
        .map { it }

fun generateRandomNumbersWithDuplicates(intervalMax: UInt, count: UInt, duplicates: UInt): List<Int> =
    (1u..intervalMax).toList()
        .shuffled()
        .take(count.toInt())
        .flatMap { number ->
            List(duplicates.toInt()) { number }
        }
        .map { it.toInt() }

fun generateRandomNumbersExcluding(
    intervalMax: UInt,
    count: UInt,
    excludedNumbers: List<Int>
): List<Int> {
    val availableNumbers = (1..intervalMax.toInt()).toList().filterNot { it in excludedNumbers }
    return availableNumbers.shuffled().take(count.toInt())
}
