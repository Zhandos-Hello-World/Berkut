package kz.cicada.berkut.feature.shareqr.presentation.scan

import kz.cicada.berkut.lib.core.ui.event.ActionEvent

class ScanQREvent(
    val checked: Boolean,
    val id: Int,
) : ActionEvent()