package kz.cicada.berkut.feature.auth.domain.repository

import kz.cicada.berkut.feature.auth.domain.model.LoginParams

interface AuthRepository {
    suspend fun loginUser(params: LoginParams)
}