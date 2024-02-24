package kz.cicada.berkut.lib.core.ui.compose.event

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Центральное событие приложения, все события должны наследоваться от него
 */
@Parcelize
sealed class SystemEvent : Parcelable

/**
 * Событие связанное с действием внутри приложения любого рода. Например,
 * возвращание какого-либо значения в результате действий пользователя
 */
open class ActionEvent : SystemEvent()

/**
 * Событие связанное с навигацией. Все навигационные события наследуются от него
 */
open class NavigationEvent : SystemEvent()