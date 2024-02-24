package kz.cicada.berkut.core.message

import kz.cicada.berkut.core.localization.string.VmRes
import java.util.UUID

data class Message(
    val id: UUID = UUID.randomUUID(),
    val title: VmRes<CharSequence>? = null,
    val description: VmRes<CharSequence>,
    val positiveButtonText: VmRes<CharSequence>? = null,
    val positiveButtonAction: (() -> Unit)? = null,
    val negativeButtonText: VmRes<CharSequence>? = null,
    val negativeButtonAction: (() -> Unit)? = null,
)