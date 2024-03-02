package kz.cicada.berkut.lib.core.ui.compose.activity

import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import org.koin.core.component.KoinComponent

class ActivityProvider : KoinComponent {

    private val activityStateFlow = MutableStateFlow<AppCompatActivity?>(null)

    val activity: ComponentActivity? get() = activityStateFlow.value

    fun attachActivity(activity: AppCompatActivity) {
        activityStateFlow.value = activity
    }

    fun detachActivity() {
        activityStateFlow.value = null
    }

    suspend fun awaitActivity(): AppCompatActivity {
        return activityStateFlow.filterNotNull().first()
    }
}