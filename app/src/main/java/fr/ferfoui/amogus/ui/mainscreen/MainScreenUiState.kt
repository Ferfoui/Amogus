package fr.ferfoui.amogus.ui.mainscreen

data class MainScreenUiState (
    val currentRandomNumbers: List<Int> = emptyList(),
    val intervalMax: UInt = 0u,
    val excludedNumbers: List<Int> = emptyList()
)