package fr.ferfoui.amogus.ui.mainscreen

import androidx.lifecycle.ViewModel
import fr.ferfoui.amogus.data.random.RandomRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class RandomViewModel(private val randomRepository: RandomRepository) : ViewModel() {

    private lateinit var randomNumbers: List<Int>

    private val _uiState = MutableStateFlow(MainScreenUiState())
    val uiState: StateFlow<MainScreenUiState> = _uiState.asStateFlow()

    fun setIntervalMax(intervalMax: UInt) {
        randomRepository.intervalMax = intervalMax
    }

    fun setCount(count: UInt) {
        randomRepository.count = count
    }

    fun setDuplicates(duplicates: UInt) {
        randomRepository.duplicates = duplicates
    }

    fun generateNumbers() {
        randomNumbers = randomRepository.generateRandomNumbers()
        _uiState.value = MainScreenUiState(randomNumbers)
    }

}