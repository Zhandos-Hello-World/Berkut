package kz.cicada.berkut.feature.language.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kz.cicada.berkut.lib.core.error.handling.language.Language
import kz.cicada.berkut.feature.language.data.source.LanguagePreferences
import kz.cicada.berkut.feature.language.domain.LanguageRepository

internal class DefaultLanguageRepository(private val dataStore: DataStore<Preferences>) :
    LanguageRepository {

    override suspend fun getLanguage(): Language? {
        return dataStore.data
            .map { preferences ->
                val language = preferences.toMutablePreferences()
                    .asMap()[LanguagePreferences.LANGUAGE]?.toString()
                if (language != null) {
                    Language.valueOf(language)
                } else {
                    null
                }
            }.firstOrNull()
    }

    override suspend fun setLanguage(language: Language) {
        dataStore.edit { preferences ->
            preferences[LanguagePreferences.LANGUAGE] = language.name
        }
    }
}
