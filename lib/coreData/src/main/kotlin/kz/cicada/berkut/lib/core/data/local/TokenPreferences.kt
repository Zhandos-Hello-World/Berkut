package kz.cicada.berkut.lib.core.data.local

import android.content.Context
import kz.cicada.berkut.lib.core.data.UserScope

interface TokenPreferences {
    fun setJWT(jwt: String)
    fun setRefreshToken(refresh: String)
    fun getJWT(): String
    fun getRefreshToken(): String
}

class DefaultTokensPreferences(context: Context) : TokenPreferences {
    private var sp = context.getSharedPreferences("UserPreferences", Context.MODE_PRIVATE)
    private var editor = sp.edit()


    override fun setJWT(jwt: String) {
        UserScope.jwtToken = jwt
        editor.putString(USER_JWT, jwt)
    }

    override fun setRefreshToken(refresh: String) {
        UserScope.refreshToken = refresh
        editor.putString(USER_TOKEN, refresh)
    }

    override fun getJWT(): String {
        return sp.getString(USER_JWT, "").toString()
    }

    override fun getRefreshToken(): String {
        return sp.getString(USER_TOKEN, "").toString()
    }

    companion object {
        const val USER_JWT = "USER_JWT"
        const val USER_TOKEN = "USER_TOKEN"
    }

}