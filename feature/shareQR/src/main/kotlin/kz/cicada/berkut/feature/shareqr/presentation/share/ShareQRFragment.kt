package kz.cicada.berkut.feature.shareqr.presentation.share

import android.os.Bundle
import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.google.gson.Gson
import kz.cicada.berkut.feature.shareqr.presentation.share.socket.QRSocketBehavior
import kz.cicada.berkut.feature.shareqr.presentation.share.socket.QRSocketModel
import kz.cicada.berkut.feature.socketconnection.presentation.SocketLauncher
import kz.cicada.berkut.feature.socketconnection.presentation.SocketViewModel
import kz.cicada.berkut.lib.core.ui.compose.base.ComposeBottomSheetFragment
import kz.cicada.berkut.lib.core.ui.compose.extension.collectAsStateWithLifecycle
import kz.cicada.berkut.lib.core.ui.navigation.FragmentTransition
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ShareQRFragment : ComposeBottomSheetFragment(), FragmentTransition.BottomTop {
    override val viewModel: ShareQRViewModel by viewModel()

    private val socketviewModel: SocketViewModel by viewModel(
        parameters = {
            parametersOf(
                SocketLauncher(
                    behavior = QRSocketBehavior(),
                ),
            )
        },
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        socketviewModel.start()
        socketviewModel.messageState.observe(this) { message ->
            try {
                val msg = message.orEmpty()
                val qrData = Gson().fromJson(
                    msg,
                    QRSocketModel::class.java,
                )
                viewModel.saveDataAndExit(qrData)
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }

    @Composable
    override fun Content() {
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        ShareQRContent(
            uiState = uiState,
            controller = viewModel,
        )
    }
}