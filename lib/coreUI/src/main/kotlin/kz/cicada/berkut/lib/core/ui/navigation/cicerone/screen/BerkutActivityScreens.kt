package kz.cicada.berkut.lib.core.ui.navigation.cicerone.screen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.github.terrakok.cicerone.androidx.ActivityScreen
import com.github.terrakok.cicerone.androidx.Creator

interface BerkutActivityScreens : ActivityScreen, BerkutScreen {

    companion object {
        operator fun invoke(
            key: String? = null,
            startActivityOptions: Bundle? = null,
            intentCreator: Creator<Context, Intent>,
        ) = object : BerkutActivityScreens {
            override val screenKey = key ?: intentCreator::class.java.name
            override val startActivityOptions = startActivityOptions
            override fun createIntent(context: Context): Intent = intentCreator.create(context)
        }
    }
}