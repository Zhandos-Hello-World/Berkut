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
    private val tokenPreferences: TokenPreferences,
) {

    suspend fun setData(
        id: String,
        refreshToken: String,
        jwt: String,
    ) {
        tokenPreferences.setRefreshToken(refreshToken)
        tokenPreferences.setJWT(jwt)
        dataStore.edit { preferences ->
            preferences[USER_ID] = id
        }
    }

    suspend fun setUserType(
        type: String,
        username: String,
        jwtToken: String,
        refreshToken: String,
        phoneNumber: String,
    ) {
        dataStore.edit { preferences ->
            preferences[USER_TYPE] = type
            preferences[USER_NAME] = username
            preferences[JWT_TOKEN] = jwtToken
            preferences[REFRESH_TOKEN] = refreshToken
            preferences[PHONE_NUMBER] = phoneNumber
        }
    }

    suspend fun setAuth(isAuth: Boolean) {
        dataStore.edit { preferences ->
            preferences[AUTHORIZED] = isAuth
        }
    }

    fun getUserName(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[USER_NAME].orEmpty()
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
    }
}