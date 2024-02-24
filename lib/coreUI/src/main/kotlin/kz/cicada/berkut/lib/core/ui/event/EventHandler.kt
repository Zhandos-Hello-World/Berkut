package kz.cicada.berkut.lib.core.ui.event

import android.os.Parcelable
import androidx.annotation.StringRes
import kotlinx.parcelize.Parcelize
import kz.cicada.berkut.lib.core.ui.navigation.cicerone.screen.BerkutScreen

interface EventHandler {
    fun onActionEvent(event: ActionEvent)
    fun onBackPressed(): (() -> Unit)?
}

@Parcelize
sealed class SystemEvent : Parcelable

/**
 * NavigationEvent can be used to:
 * 1) navigate to other activity
 * 2) navigate to other fragment
 * 3) show dialogs of DialogFragment (including BottomSheetDialogFragment), if pointed through navigation graph
 */
/**
 * Intended for local events that handle on EndpointFragment
 */
open class ActionEvent : SystemEvent()

object NoInternetErrorEvent : ActionEvent()
object TimeoutErrorEvent : ActionEvent()

object OpenMainActivityEvent : ActionEvent()
object OpenAuthFlowEvent : ActionEvent()

object CallBackPressedEvent : ActionEvent()
object CloseAppEvent : ActionEvent()

class OpenScreenEvent(val screen: BerkutScreen): ActionEvent()

/**
 * Intended for showing simple common error dialogs with fixed title, common to whole application
 */
class CommonErrorEvent(@StringRes val message: Int) : ActionEvent()
class NetworkErrorEvent(val errorText: String, val title: String? = null) : ActionEvent()