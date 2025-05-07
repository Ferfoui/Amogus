package fr.ferfoui.amogus.data.random

import fr.ferfoui.amogus.data.storage.DataStoreRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RandomRepository(private val dataStoreRepository: DataStoreRepository) {

    private var _intervalMax: UInt = 0u
    private var _count: UInt = 0u
    private var _duplicates: UInt = 1u
    private var _excludedNumbers: List<Int> = emptyList()

    var intervalMax: UInt
        get() = _intervalMax
        set(value) {
            _intervalMax = value
            count = _count
        }

    var count: UInt
        get() = _count
        set(value) {
            val allowedCount = (_intervalMax - _excludedNumbers.size.toUInt()) * _duplicates
            if (value > allowedCount) {
                _count = allowedCount
            }
            _count = value
        }

    var excludedNumbers: List<Int>
        get() = _excludedNumbers
        set(value) {
            _excludedNumbers = value
            count = _count
        }

    fun generateRandomNumbers(): List<Int> {
        return generateRandomNumbersExcluding(
            intervalMax = _intervalMax,
            count = _count,
            excludedNumbers = _excludedNumbers
        )
    }

    fun saveProperties() {
        dataStoreRepository.setGeneratorProperties(_intervalMax, _count, _excludedNumbers)
    }

    fun loadProperties() {
        CoroutineScope(Dispatchers.IO).launch {
            dataStoreRepository.getIntervalMax().collect { value ->
                _intervalMax = value?.toUInt() ?: 0u
            }
        }
        CoroutineScope(Dispatchers.IO).launch {
            dataStoreRepository.getCount().collect { value ->
                _count = value?.toUInt() ?: 0u
            }
        }
        CoroutineScope(Dispatchers.IO).launch {
            dataStoreRepository.getExcludedNumbers {
                _excludedNumbers = it
            }
        }
    }

}