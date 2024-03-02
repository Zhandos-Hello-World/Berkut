package kz.cicada.berkut

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.github.terrakok.cicerone.NavigatorHolder
import kotlinx.coroutines.launch
import kz.cicada.berkut.databinding.ActivityMainBinding
import kz.cicada.berkut.feature.language.navigation.LanguageScreens
import kz.cicada.berkut.feature.maps.navigation.MapsScreen
import kz.cicada.berkut.lib.core.ui.compose.activity.ActivityProvider
import kz.cicada.berkut.lib.core.ui.event.EventObserver
import kz.cicada.berkut.lib.core.ui.event.OpenAuthFlowEvent
import kz.cicada.berkut.lib.core.ui.event.OpenExternalLinkEvent
import kz.cicada.berkut.lib.core.ui.event.OpenMainActivityEvent
import kz.cicada.berkut.lib.core.ui.extensions.openInBrowser
import kz.cicada.berkut.lib.core.ui.navigation.MainActivityNavigation
import kz.cicada.berkut.lib.core.ui.navigation.cicerone.BerkutNavigator
import kz.cicada.berkut.lib.core.ui.navigation.cicerone.router.RouterFacade
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(), MainActivityNavigation {

    private lateinit var binding: ActivityMainBinding
    private val activityProvider: ActivityProvider by inject()


    private val viewModel: MainActivityViewModel by inject()

    private val navigator = BerkutNavigator(activity = this, containerId = R.id.fcv_main_activity)
    private val navigatorHolder: NavigatorHolder by inject()
    private val routerFacade: RouterFacade by inject()

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        activityProvider.attachActivity(activity = this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                val isAuth = viewModel.isAuth()
                binding.bottomNav.isVisible = isAuth
                if (isAuth) {
                    openMainFlow()
                } else {
                    openAuthFlow()
                }
            }
        }

        setupBottomNavigationView()
        observeViewModelEvents()
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        activityProvider.detachActivity()
    }

    override fun openMainFlow() {
        binding.bottomNav.isVisible = true
        routerFacade.newRootScreen(MapsScreen.Main())
    }

    override fun openAuthFlow(logOut: Boolean) {
        routerFacade.newRootScreen(LanguageScreens.Onboarding())
    }

    override fun openMapTab() {
        routerFacade.newRootScreen(MapsScreen.Main())
    }

    override fun openChildTab() = Unit

    override fun openHomeTab() = Unit

    override fun openParentTab() = Unit


    private fun setupBottomNavigationView() {
        binding.bottomNav.setOnItemSelectedListener { item ->
            item.isCheckable = true
            item.isChecked = true
            when (item.itemId) {
                R.id.map_page -> routerFacade.newRootScreen(screen = MapsScreen.Main())
                R.id.profile_page -> routerFacade
            }
            true
        }
    }

    private fun observeViewModelEvents() {
        viewModel.actionEvents.observe(
            this,
            EventObserver {
                when (it) {
                    is OpenMainActivityEvent -> openMainFlow()
                    is OpenAuthFlowEvent -> openAuthFlow()
                    is OpenExternalLinkEvent -> it.link.openInBrowser(this)
                    else -> Unit
                }
            },
        )
    }
}