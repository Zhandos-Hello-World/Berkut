package kz.cicada.berkut.lib.core.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreferences(
    private val dataStore: DataStore<Preferences>,
) {

    suspend fun setUserType(
        id: String,
        type: String,
        username: String,
        jwtToken: String,
        refreshToken: String,
        phoneNumber: String,
    ) {
        dataStore.edit { preferences ->
            preferences[USER_ID] = id
            preferences[USER_TYPE] = type
            preferences[USER_NAME] = username
            preferences[JWT_TOKEN] = jwtToken
            preferences[REFRESH_TOKEN] = refreshToken
            preferences[PHONE_NUMBER] = phoneNumber
        }
    }

    suspend fun setUsername(username: String) {
        dataStore.edit { preferences ->
            preferences[USER_NAME] = username
        }
    }

    suspend fun setSecondUserType(
        id: String,
        type: String,
        username: String,
        phoneNumber: String,
        rel: String,
        href: String,
    ) {
        dataStore.edit { preferences ->
            preferences[SECOND_USER_ID] = id
            preferences[SECOND_USER_TYPE] = type
            preferences[SECOND_USER_NAME] = username
            preferences[SECOND_PHONE_NUMBER] = phoneNumber
            preferences[SECOND_REL] = rel
            preferences[SECOND_HREF] = href
        }
    }

    suspend fun setAuth(isAuth: Boolean) {
        dataStore.edit { preferences ->
            preferences[AUTHORIZED] = isAuth
        }
    }

    suspend fun clearAllData() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    fun getUserName(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[USER_NAME].orEmpty()
        }
    }

    fun getUserPhoneNumber() : Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[PHONE_NUMBER].orEmpty()
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

    fun getJWT(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[JWT_TOKEN].orEmpty()
        }
    }

    companion object {
        private val USER_ID = stringPreferencesKey("USER_ID")

        private val USER_TYPE = stringPreferencesKey("USER_TYPE")
        private val USER_NAME = stringPreferencesKey("USER_NAME")
        private val PHONE_NUMBER = stringPreferencesKey("USER_PHONE_NUMBER")

        private val JWT_TOKEN = stringPreferencesKey("JWT_TOKEN")
        private val REFRESH_TOKEN = stringPreferencesKey("REFRESH_TOKEN")


        private val AUTHORIZED = booleanPreferencesKey("AUTHORIZED")

        private val SECOND_USER_ID = stringPreferencesKey("SECOND_USER_ID")
        private val SECOND_USER_TYPE = stringPreferencesKey("SECOND_USER_TYPE")
        private val SECOND_USER_NAME = stringPreferencesKey("SECOND_USER_NAME")
        private val SECOND_PHONE_NUMBER = stringPreferencesKey("SECOND_USER_PHONE_NUMBER")
        private val SECOND_REL = stringPreferencesKey("SECOND_REL")
        private val SECOND_HREF = stringPreferencesKey("SECOND_HREF")
    }
}