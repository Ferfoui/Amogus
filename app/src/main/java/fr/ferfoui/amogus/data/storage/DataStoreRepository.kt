package fr.ferfoui.amogus.data.storage

import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class DataStoreRepository(
    private val dataStoreManager: DataStoreManager
) {

    fun setGeneratorProperties(
        intervalMax: UInt,
        count: UInt,
        excludedNumbers: List<Int>
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            setIntervalMax(intervalMax)
        }

        CoroutineScope(Dispatchers.IO).launch {
            setCount(count)
        }

        CoroutineScope(Dispatchers.IO).launch {
            setExcludedNumbers(excludedNumbers)
        }
    }

    private suspend fun setIntervalMax(value: UInt) {
        setValue(INTERVAL_MAX_KEY, value.toInt())
    }

    private suspend fun setCount(value: UInt) {
        setValue(COUNT_KEY, value.toInt())
    }

    private suspend fun setExcludedNumbers(value: List<Int>) {
        setValue(EXCLUDED_NUMBERS_KEY, value.map { it.toString() }.toSet())
    }

    private suspend fun <T> setValue(key: Preferences.Key<T>, value: T) {
        dataStoreManager.setData(key, value)
    }

    fun getIntervalMax(): Flow<Int?> {
        return dataStoreManager.getData(INTERVAL_MAX_KEY)
    }

    fun getCount(): Flow<Int?> {
        return dataStoreManager.getData(COUNT_KEY)
    }

    suspend fun getExcludedNumbers(collector: (List<Int>) -> Unit) {
        dataStoreManager.getData(EXCLUDED_NUMBERS_KEY).collect { set ->
            collector(set?.map { it.toInt() } ?: emptyList())
        }
    }
}