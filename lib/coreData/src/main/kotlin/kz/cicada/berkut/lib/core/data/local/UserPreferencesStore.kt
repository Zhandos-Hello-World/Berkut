package kz.cicada.berkut.lib.core.data.local

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore


private const val USER_PREFERENCES_NAME = "user_preferences"

internal val Context.dataStore by preferencesDataStore(
    name = USER_PREFERENCES_NAME
)