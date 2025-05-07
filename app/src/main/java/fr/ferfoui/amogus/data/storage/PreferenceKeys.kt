package fr.ferfoui.amogus.data.storage

import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey

val INTERVAL_MAX_KEY = intPreferencesKey("interval_max")
val COUNT_KEY = intPreferencesKey("count")
val EXCLUDED_NUMBERS_KEY = stringSetPreferencesKey("excluded_numbers")
