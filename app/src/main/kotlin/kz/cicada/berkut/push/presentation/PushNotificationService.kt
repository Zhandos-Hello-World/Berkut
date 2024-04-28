package kz.cicada.berkut.push.presentation

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kz.cicada.berkut.push.domain.repository.PushRepository
import org.koin.android.ext.android.inject
import org.koin.core.component.KoinComponent

class PushNotificationService : FirebaseMessagingService(), KoinComponent {
    private val repo: PushRepository by inject()

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("onNewToken", token)
        CoroutineScope(Dispatchers.Main.immediate + SupervisorJob()).launch {
            try {
                repo.updateFCMToken(token)
            } catch (ex: CancellationException) {

            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        Log.d("onMessageReceived", message.toString())

    }

}