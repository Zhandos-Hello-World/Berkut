package kz.cicada.berkut.feature.location.external

interface ExternalLocationService {
    fun startLocationService()
    fun closeLocationService()
}