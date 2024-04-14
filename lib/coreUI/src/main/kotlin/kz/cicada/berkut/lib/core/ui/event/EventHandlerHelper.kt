package kz.cicada.berkut.lib.core.ui.event

import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import kz.cicada.berkut.core.presentation.R
import kz.cicada.berkut.lib.core.ui.extensions.observeNavigationResult
import kz.cicada.berkut.lib.core.ui.extensions.openFile
import kz.cicada.berkut.lib.core.ui.extensions.openInBrowser
import kz.cicada.berkut.lib.core.ui.extensions.postNavigationResult
import kz.cicada.berkut.lib.core.ui.extensions.shareFile
import kz.cicada.berkut.lib.core.ui.extensions.showAlertDialog
import kz.cicada.berkut.lib.core.ui.extensions.showAlertDialogIntRes
import kz.cicada.berkut.lib.core.ui.navigation.MainActivityNavigation
import kz.cicada.berkut.lib.core.ui.navigation.cicerone.router.RouterFacade
import kz.cicada.berkut.lib.core.ui.navigation.cicerone.screen.BerkutScreen
import kz.cicada.berkut.lib.core.ui.utils.DeviceUtils
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class EventHandlerHelper : KoinComponent {

    private val routerFacade: RouterFacade by inject()

    fun observeViewModel(
        handler: EventHandler,
        lifecycleOwner: LifecycleOwner,
        baseViewModel: kz.cicada.berkut.lib.core.ui.base.BaseViewModel?,
    ) {
        val fragment = handler as Fragment
        baseViewModel?.actionEvents?.takeIf { !it.hasObservers() }
            ?.observe(
                lifecycleOwner,
                EventObserver { event ->
                    when (event) {
                        is NoInternetErrorEvent   -> {
                            fragment.showAlertDialogIntRes(
                                title = R.string.text_no_internet,
                                message = R.string.text_check_connection,
                            )
                        }
                        is CommonErrorEvent       -> {
                            fragment.showAlertDialogIntRes(message = event.message)
                        }
                        is NetworkErrorEvent      -> {
                            fragment.showAlertDialog(
                                message = event.errorText,
                                title = event.title,
                            )
                        }
                        is ResourceBlocked        -> {
                            val secondsText = fragment.resources.getQuantityString(
                                R.plurals.seconds,
                                event.secondsLeft,
                                event.secondsLeft,
                            )
                            val message = fragment.getString(R.string.resource_blocked, secondsText)
                            fragment.showAlertDialog(message = message)
                        }
                        is OpenMainActivityEvent  -> {
                            fragment.activity?.apply {
                                val nav = this as MainActivityNavigation
                                nav.openMainFlow()
                            }
                        }
                        is TokenExpiredErrorEvent -> {
                            fragment.showAlertDialogIntRes(
                                title = R.string.session_expired,
                                message = R.string.please_auth_again,
                                positiveClick = { _, _ -> openAuthFlow(fragment) },
                                isCancelable = true,
                            )
                        }
                        is CloseAppEvent          -> {
                            fragment.requireActivity().finishAffinity()
                        }
                        is OpenAuthFlowEvent      -> {
                            openAuthFlow(fragment)
                        }
                        is OpenScreenEvent ->  {
                            routerFacade.navigateTo(event.screen)
                        }
                        is NewRootScreen -> {
                            routerFacade.newRootScreen(event.screen)
                        }
                        is ReplaceScreenEvent -> {
                            routerFacade.replaceScreen(event.screen)
                        }
                        is CloseScreenEvent -> {
                            routerFacade.exit()
                        }
                        is CallBackPressedEvent   -> {
                            fragment.activity?.onBackPressed()
                        }
                        is ActionResultEvent      -> {
                            event.result?.let { fragment.postNavigationResult(it, event.key) }
                            if (event.backToScreenKey != null) {
                                routerFacade.backTo(
                                    object : BerkutScreen {
                                        override val screenKey: String = event.backToScreenKey
                                    },
                                )
                            } else {
                                routerFacade.exit()
                            }
                        }
                        is ActionPostBackEvent    -> {
                            event.result?.let { fragment.postNavigationResult(it) }
                        }
                        is ShareFileEvent         -> {
                            fragment.shareFile(event.filename, event.bytes)
                        }
                        is OpenFileEvent          -> {
                            fragment.context?.openFile(event)
                        }
                        is OpenExternalLinkEvent  -> {
                            event.link.openInBrowser(fragment.requireContext())
                        }
                        is CallEvent              -> {
                            DeviceUtils.call(
                                context = fragment.context,
                                phone = event.number,
                            )
                        }
                        is ActionEvent            -> {
                            handler.onActionEvent(event)
                        }
                    }
                },
            )

        fragment.observeNavigationResult(fragment) {
            baseViewModel?.onNavigationResult(it)
        }
    }

    private fun openAuthFlow(fragment: Fragment, logOut: Boolean = false) {
        (fragment.activity as MainActivityNavigation?)?.apply {
            openAuthFlow(logOut = logOut)
        }
    }
}