package fr.ferfoui.amogus.ui.mainscreen

data class MainScreenUiState (
    val currentRandomNumbers: List<Int> = emptyList(),
    val generatingCount: UInt = 0u,
    val intervalMax: UInt = 0u
)