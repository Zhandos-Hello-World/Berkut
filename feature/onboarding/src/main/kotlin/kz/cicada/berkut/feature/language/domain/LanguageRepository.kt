package kz.cicada.berkut.feature.language.domain

import kz.cicada.berkut.lib.core.error.handling.language.Language

interface LanguageRepository {
    suspend fun getLanguage(): Language?
    suspend fun setLanguage(language: Language)
}
