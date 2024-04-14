package kz.cicada.berkut

//import com.google.firebase.ktx.Firebase
//import com.google.firebase.messaging.ktx.messaging
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kz.cicada.berkut.feature.location.external.ExternalLocationService
import kz.cicada.berkut.lib.core.data.local.UserPreferences
import kz.cicada.berkut.lib.core.data.network.UserType
import kz.cicada.berkut.lib.core.ui.base.BaseViewModel

internal class MainActivityViewModel(
    private val userPreferences: UserPreferences,
    private val dispatcher: CoroutineDispatcher,
    private val externalLocationService: ExternalLocationService,
) : BaseViewModel() {
    suspend fun isAuth(): Boolean = userPreferences.getAuth().first()

    fun checkAndRunGeoService() {
        viewModelScope.launch {
            if (userPreferences.getType().first() == UserType.CHILD.name) {
                externalLocationService.startLocationService()

                Log.d("JWTToken", userPreferences.getJWT().first())

            }

            FirebaseMessaging.getInstance().token.addOnCompleteListener(
                OnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        Log.w("FCM", "Fetching FCM registration token failed", task.exception)
                        return@OnCompleteListener
                    }

                    val token = task.result
                    Log.d("FCM", token)
                },
            )

//            val localFirebase = Firebase.messaging.token.await()
//            Log.d("FCM", localFirebase)
        }
    }
}