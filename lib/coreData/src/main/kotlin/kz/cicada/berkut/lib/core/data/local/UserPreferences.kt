package kz.cicada.berkut.lib.core.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesOf
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreferences(
    private val dataStore: DataStore<Preferences>,
) {

    suspend fun setData(
        id: String,
        refreshToken: String,
        jwt: String,
    ) {
        dataStore.edit { preferences ->
            preferences[USER_ID] = id
            preferences[USER_REFRESH_TOKEN] = refreshToken
            preferences[USER_JWT] = jwt

        }
    }

    suspend fun setUserType(
        type: String,
        username: String,
        phoneNumber: String,
    ) {
        dataStore.edit { preferences ->
            preferences[USER_TYPE] = type
            preferences[USER_NAME] = username
            preferences[PHONE_NUMBER] = phoneNumber
        }
    }

    suspend fun setAuth(isAuth: Boolean) {
        dataStore.edit { preferences ->
            preferences[AUTHORIZED] = isAuth
        }
    }

    fun getAuth(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[AUTHORIZED] ?: false
        }
    }

    fun getId(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[USER_ID].orEmpty()
        }
    }


    fun getType(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[USER_TYPE].orEmpty()
        }
    }

    companion object {
        private val USER_ID = stringPreferencesKey("USER_ID")
        private val USER_REFRESH_TOKEN = stringPreferencesKey("USER_TOKEN")
        private val USER_JWT = stringPreferencesKey("USER_JWT")

        private val USER_TYPE = stringPreferencesKey("USER_TYPE")
        private val USER_NAME = stringPreferencesKey("USER_NAME")
        private val PHONE_NUMBER = stringPreferencesKey("USER_PHONE_NUMBER")


        private val AUTHORIZED = booleanPreferencesKey("AUTHORIZED")
    }
}