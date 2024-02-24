package kz.cicada.berkut.lib.core.ui.event

import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import kz.cicada.berkut.lib.core.localization.string.VmRes

object EventCloseKeyboard : ActionEvent()
object TokenExpiredErrorEvent : ActionEvent()

object ShowFileChooserEvent : ActionEvent()
class BlockingLoaderEvent(val show: Boolean) : ActionEvent()
class ShowSnackBarEvent(@StringRes val id: Int) : ActionEvent()
class ShowSnackBarStringEvent(val text: String) : ActionEvent()
class ShowSnackBarUiTextEvent(val text: VmRes.StrRes) : ActionEvent()
class ShowToastEvent(@StringRes val id: Int) : ActionEvent()
class ShowToastEventText(val text: String) : ActionEvent()
class ResourceBlocked(val secondsLeft: Int) : ActionEvent()
class ActionResultEvent(
    val result: Any? = null,
    val key: String? = null,
    val backToScreenKey: String? = null,
) : ActionEvent()

class ActionPostBackEvent(val result: Any? = null) : ActionEvent()
class ShareFileEvent(val filename: String, val bytes: ByteArray) : ActionEvent()
class OpenFileEvent(val filename: String, val bytes: ByteArray? = null) : ActionEvent()
class OpenExternalLinkEvent(val link: String) : ActionEvent()
class CallEvent(val number: String) : ActionEvent()
class GetFragmentActivityEvent(val action: FragmentActivity.() -> Unit) : ActionEvent()
class GetFragmentEvent(val action: Fragment.() -> Unit) : ActionEvent()
class ShowAlertDialogEvent(
    val title: VmRes.StrRes? = null,
    val message: VmRes.StrRes? = null,
    val positiveButton: VmRes.StrRes? = null,
    val positiveButtonClick: (() -> Unit)? = null,
    val negativeButton: VmRes.StrRes? = null,
    val negativeButtonClick: (() -> Unit)? = null,
    val cancellable: Boolean? = null,
) : ActionEvent()