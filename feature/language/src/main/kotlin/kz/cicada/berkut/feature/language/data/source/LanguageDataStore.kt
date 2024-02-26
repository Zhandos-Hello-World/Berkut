package kz.cicada.berkut.feature.language.data.source

import android.content.Context
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

private const val LANGUAGE_DATA_STORE = "LANGUAGE_DATA_STORE"

val Context.languageDataStore by preferencesDataStore(
    name = LANGUAGE_DATA_STORE,
    corruptionHandler = null,
    produceMigrations = { listOf() },
    scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
)

object LanguagePreferences {

    val LANGUAGE = stringPreferencesKey("APP_LANGUAGE")
}
