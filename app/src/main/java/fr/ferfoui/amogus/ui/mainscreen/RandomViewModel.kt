package fr.ferfoui.amogus.ui.mainscreen

import androidx.lifecycle.ViewModel
import fr.ferfoui.amogus.data.random.RandomRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class RandomViewModel(private val randomRepository: RandomRepository) : ViewModel() {

    init {
        randomRepository.loadProperties()
    }

    private lateinit var randomNumbers: List<Int>

    private val _uiState = MutableStateFlow(MainScreenUiState())
    val uiState: StateFlow<MainScreenUiState> = _uiState.asStateFlow()

    var intervalMax: UInt
        get() = randomRepository.intervalMax
        set(value) {
            randomRepository.intervalMax = value
        }

    var count: UInt
        get() = randomRepository.count
        set(value) {
            randomRepository.count = value
        }

    var excludedNumbers: List<Int>
        get() = randomRepository.excludedNumbers
        set(value) {
            randomRepository.excludedNumbers = value
        }

    fun generateNumbers() {
        randomNumbers = randomRepository.generateRandomNumbers()
        _uiState.value = MainScreenUiState(
            currentRandomNumbers = randomNumbers,
            intervalMax = intervalMax,
            excludedNumbers = excludedNumbers
        )
    }

    fun saveProperties() {
        randomRepository.saveProperties()
    }

}