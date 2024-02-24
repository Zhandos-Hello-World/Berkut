package kz.cicada.berkut.lib.core.ui.event

import androidx.annotation.StringRes
import java.io.Serializable

interface EventHandler {
    fun onActionEvent(event: ActionEvent)
    fun onBackPressed(): (() -> Unit)?
}

sealed class SystemEvent : Serializable

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

/**
 * Intended for showing simple common error dialogs with fixed title, common to whole application
 */
class CommonErrorEvent(@StringRes val message: Int) : ActionEvent()
class NetworkErrorEvent(val errorText: String, val title: String? = null) : ActionEvent()