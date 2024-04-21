package kz.cicada.berkut

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.github.terrakok.cicerone.NavigatorHolder
import kotlinx.coroutines.launch
import kz.cicada.berkut.behaviors.AddTaskChildrenBehavior
import kz.cicada.berkut.behaviors.LocationChildrenBehavior
import kz.cicada.berkut.databinding.ActivityMainBinding
import kz.cicada.berkut.feature.children.navigation.ChildrenScreens
import kz.cicada.berkut.feature.children.presentation.childs.ChildrenLauncher
import kz.cicada.berkut.feature.language.navigation.LanguageScreens
import kz.cicada.berkut.feature.maps.navigation.MapsScreen
import kz.cicada.berkut.feature.maps.presentation.MapsFragment
import kz.cicada.berkut.feature.profile.navigation.ProfileScreens
import kz.cicada.berkut.feature.uploadphoto.presentation.navigation.AddAvatarScreen
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
    private var currentPage: Int? = null
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

        requestNotificationPermission()

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
        viewModel.checkAndRunGeoService()
        binding.bottomNav.isVisible = true
        openMapTab()
    }

    override fun openAuthFlow(logOut: Boolean) {
        routerFacade.newRootChain(LanguageScreens.Onboarding())
        binding.bottomNav.isVisible = false
    }

    override fun openMapTab() {
        routerFacade.newRootChain(MapsScreen.Main())
    }

    override fun openChildTab() {
        routerFacade.newRootChain(
            ChildrenScreens.ChildrenScreen(
                launcher = ChildrenLauncher(
                    behavior = AddTaskChildrenBehavior()
                ),
            )
        )
    }

    override fun openHomeTab() = routerFacade.newRootChain(ProfileScreens.Home())

    override fun openLocationsTab() = routerFacade.newRootChain(
        ChildrenScreens.ChildrenScreen(
            launcher = ChildrenLauncher(
                behavior = LocationChildrenBehavior()
            ),
        )
    )


    private fun setupBottomNavigationView() {
        binding.bottomNav.setOnItemSelectedListener { item ->
            item.isCheckable = true
            item.isChecked = true

            if (item.itemId == currentPage) {
                false
            } else {
                when (item.itemId) {
                    R.id.map_page -> openMapTab()
                    R.id.profile_page -> openHomeTab()
                    R.id.my_children -> openChildTab()
                    R.id.locations_page -> openLocationsTab()
                }
                currentPage = item.itemId
                true
            }
        }
    }

    private fun observeViewModelEvents() {
        viewModel.actionEvents.observe(
            this,
            EventObserver {
                when (it) {
                    is OpenMainActivityEvent -> {
                        viewModel.checkAndRunGeoService()
                        openMainFlow()
                    }

                    is OpenAuthFlowEvent -> {
                        openAuthFlow()
                    }

                    is OpenExternalLinkEvent -> it.link.openInBrowser(this)
                    else -> Unit
                }
            },
        )
    }

    private fun requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val hasPermission = ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS,
            ) == PackageManager.PERMISSION_GRANTED

            if (!hasPermission) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    0,
                )
            }
        }
    }
}