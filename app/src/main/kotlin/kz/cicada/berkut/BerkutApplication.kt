package kz.cicada.berkut

import android.app.Application
import kz.cicada.berkut.di.KoinModules

internal class BerkutApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoinModules()
    }

    private fun initKoinModules() {
        KoinModules.start(this)
    }
}