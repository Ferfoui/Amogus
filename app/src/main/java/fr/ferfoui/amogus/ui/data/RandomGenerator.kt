package fr.ferfoui.amogus.ui.data

fun generateRandomNumber(intervalMax: Int): Int {
    return (0..intervalMax).random()
}

fun generateRandomNumbers(intervalMax: Int): List<Int> {
    return (0 until intervalMax).map { generateRandomNumber(intervalMax) }
}
