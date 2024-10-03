package fr.ferfoui.amogus.ui.mainscreen

import androidx.lifecycle.ViewModel
import fr.ferfoui.amogus.data.random.generateDistinctRandomNumbers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainScreenViewModel : ViewModel() {

    private lateinit var randomNumbers: List<Int>

    private val _uiState = MutableStateFlow(MainScreenUiState())
    val uiState: StateFlow<MainScreenUiState> = _uiState.asStateFlow()

    fun generateNumbers(intervalMax: Int, count: Int) {
        randomNumbers = generateRandomNumbers(intervalMax, count)
        _uiState.value = MainScreenUiState(randomNumbers)
    }

    private fun generateRandomNumbers(intervalMax: Int, count: Int): List<Int> {
        return generateDistinctRandomNumbers(intervalMax, count)
    }

}