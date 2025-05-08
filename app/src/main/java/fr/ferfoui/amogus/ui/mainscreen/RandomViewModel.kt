package fr.ferfoui.amogus.ui.mainscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.ferfoui.amogus.data.random.RandomRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RandomViewModel(private val randomRepository: RandomRepository) : ViewModel() {

    init {
        randomRepository.loadProperties(viewModelScope) {
            _uiState.value = _uiState.value.copy(
                generatingCount = count,
                intervalMax = intervalMax
            )
        }
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
        viewModelScope.launch {
            randomNumbers = randomRepository.generateRandomNumbers()
            _uiState.value = _uiState.value.copy(currentRandomNumbers = randomNumbers)
        }
    }

    fun saveProperties() {
        randomRepository.saveProperties()
        _uiState.value = _uiState.value.copy(
            generatingCount = count,
            intervalMax = intervalMax
        )
    }

}