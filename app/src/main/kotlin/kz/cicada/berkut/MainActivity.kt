package kz.cicada.berkut

import am.strongte.hub.auth.navigation.AuthScreens
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.terrakok.cicerone.NavigatorHolder
import kz.cicada.berkut.databinding.ActivityMainBinding
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

	private val viewModel: MainActivityViewModel by inject()

	private val navigator = BerkutNavigator(activity = this, containerId = R.id.fcv_main_activity)
	private val navigatorHolder: NavigatorHolder by inject()
	private val routerFacade: RouterFacade by inject()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)

		observeViewModelEvents()
	}

	override fun onResumeFragments() {
		super.onResumeFragments()
		navigatorHolder.setNavigator(navigator)
	}

	override fun openMainFlow() {
		//TODO
	}

	override fun openAuthFlow(logOut: Boolean) {
		routerFacade.newRootScreen(AuthScreens.Login())
	}

	private fun observeViewModelEvents() {
		viewModel.actionEvents.observe(
			this,
			EventObserver {
				when (it) {
					is OpenMainActivityEvent -> openMainFlow()
					is OpenAuthFlowEvent     -> openAuthFlow()
					is OpenExternalLinkEvent -> it.link.openInBrowser(this)
					else                     -> Unit
				}
			},
		)
	}
}