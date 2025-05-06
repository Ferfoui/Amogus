package fr.ferfoui.amogus.data.storage

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "app_preferences")

/**
 * A manager class for handling data storage using Jetpack [DataStore].
 *
 * @param context The application context.
 */
class DataStoreManager(private val context: Context) {

    /**
     * Saves a value in the [DataStore].
     *
     * @param key The key for the value to be saved.
     * @param value The value to be saved.
     * @param T The type of the value.
     */
    suspend fun <T> setData(key: Preferences.Key<T>, value: T) {
        context.dataStore.edit { preferences ->
            preferences[key] = value
        }
    }

    /**
     * Retrieves a value from the [DataStore].
     *
     * @param key The key for the value to be retrieved.
     * @param T The type of the value.
     * @return A [Flow] emitting the value associated with the key, or null if not found.
     */
    fun <T> getData(key: Preferences.Key<T>): Flow<T?> {
        return context.dataStore.data
            .catch { exception ->
                if (exception is IOException)
                    emit(emptyPreferences())
                else
                    throw exception
            }.map { preferences ->
                preferences[key]
            }
    }
}