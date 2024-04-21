package kz.cicada.berkut.feature.shareqr.presentation.share

import kz.cicada.berkut.feature.shareqr.presentation.share.socket.QRSocketModel

interface ShareQRController {
    fun saveDataAndExit(model: QRSocketModel)
    fun onNavigateBack()
}