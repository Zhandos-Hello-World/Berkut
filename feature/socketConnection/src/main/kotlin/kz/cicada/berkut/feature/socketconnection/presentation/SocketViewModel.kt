package kz.cicada.berkut.feature.socketconnection.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kz.cicada.berkut.lib.core.data.local.UserPreferences
import kz.cicada.berkut.lib.core.ui.base.BaseViewModel
import ua.naiksoftware.stomp.Stomp
import ua.naiksoftware.stomp.StompClient
import ua.naiksoftware.stomp.dto.LifecycleEvent
import ua.naiksoftware.stomp.dto.StompMessage

class SocketViewModel(
    private val userPreferences: UserPreferences,
    private val launcher: SocketLauncher,
) : BaseViewModel() {
    private var mStompClient: StompClient? = null
    private var compositeDisposable: CompositeDisposable? = null

    private val _messageState = MutableLiveData<String?>()
    val messageState: LiveData<String?> = _messageState

    fun start() {
        viewModelScope.launch(Dispatchers.IO) {
            val behavior = launcher.behavior
            if (behavior.accessableForUserType()) {
                val path = behavior.onPath()
                mStompClient = Stomp.over(
                    Stomp.ConnectionProvider.OKHTTP,
                    behavior.getBaseUrl(),
                ).withServerHeartbeat(30000)
                initChat(path)
            }
        }
    }

    private fun initChat(path: String) {
        resetSubscriptions()
        val topicSubscribe = mStompClient!!.topic(path).subscribeOn(Schedulers.io(), true)
            .observeOn(AndroidSchedulers.mainThread()).subscribe(
                { topicMessage: StompMessage ->
                    Log.d("SocketViewModel", topicMessage.payload)
                    addMessage(topicMessage.payload)
                },
                {
                    Log.e("SocketViewModel", "Error!", it)
                },
            )


        val lifecycleSubscribe = mStompClient!!.lifecycle().subscribeOn(Schedulers.io(), false)
            .observeOn(
                AndroidSchedulers.mainThread()
            ).subscribe { lifecycleEvent: LifecycleEvent ->
                when (lifecycleEvent.type!!) {
                    LifecycleEvent.Type.OPENED -> Log.d(
                        "SocketViewModel", "Stomp connection opened"
                    )

                    LifecycleEvent.Type.ERROR -> Log.e(
                        "SocketViewModel", "Error", lifecycleEvent.exception
                    )

                    LifecycleEvent.Type.FAILED_SERVER_HEARTBEAT, LifecycleEvent.Type.CLOSED -> {
                        Log.d("SocketViewModel", "Stomp connection closed")
                    }
                }
            }

        compositeDisposable!!.add(lifecycleSubscribe)
        compositeDisposable!!.add(topicSubscribe)

        if (!mStompClient!!.isConnected) {
            mStompClient!!.connect()
        }
    }

    private fun addMessage(message: String) {
        _messageState.value = message
    }

    private fun resetSubscriptions() {
        if (compositeDisposable != null) {
            compositeDisposable!!.dispose()
        }
        compositeDisposable = CompositeDisposable()
    }

    override fun onCleared() {
        mStompClient?.disconnect()
        compositeDisposable?.dispose()
    }
}