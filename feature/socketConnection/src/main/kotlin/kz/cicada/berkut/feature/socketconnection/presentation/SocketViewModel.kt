package kz.cicada.berkut.feature.socketconnection.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kz.cicada.berkut.lib.core.data.local.UserPreferences
import kz.cicada.berkut.lib.core.data.network.UserType
import kz.cicada.berkut.lib.core.ui.base.BaseViewModel
import ua.naiksoftware.stomp.Stomp
import ua.naiksoftware.stomp.StompClient
import ua.naiksoftware.stomp.dto.LifecycleEvent
import ua.naiksoftware.stomp.dto.StompMessage

class SocketViewModel(
    private val userPreferences: UserPreferences,
): BaseViewModel() {
    companion object {
        const val SOCKET_URL = "ws://berkut-mobile-app-dev.up.railway.app/ws-connection"
    }
    val geoPath: String by lazy { "/user/${id}/geo-data" }
    private var id = ""

    private var mStompClient: StompClient? = null
    private var compositeDisposable: CompositeDisposable? = null

    private val _chatState = MutableLiveData<String?>()
    val liveChatState: LiveData<String?> = _chatState

    fun start() {
        viewModelScope.launch {
            if (userPreferences.getType().first() == UserType.PARENT.name) {
                val userId = userPreferences.getId().first()
                id = userId
                mStompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP, SOCKET_URL)
                    .withServerHeartbeat(3000)
                initChat()
            }
        }
    }

    private fun initChat() {
        resetSubscriptions()

            val topicSubscribe =
                mStompClient!!.topic(geoPath)
                    .subscribeOn(Schedulers.io(), true)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ topicMessage: StompMessage ->
                        Log.d("SocketViewModel", topicMessage.payload)
                        addMessage(topicMessage.payload)
                    }, {
                        Log.e("SocketViewModel", "Error!", it)
                    })


            val lifecycleSubscribe = mStompClient!!.lifecycle().subscribeOn(Schedulers.io(), false)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { lifecycleEvent: LifecycleEvent ->
                    when (lifecycleEvent.type!!) {
                        LifecycleEvent.Type.OPENED -> Log.d("SocketViewModel", "Stomp connection opened")
                        LifecycleEvent.Type.ERROR -> Log.e("SocketViewModel", "Error", lifecycleEvent.exception)
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
        _chatState.value = message
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

    enum class TypeConnection(val path: String) {

    }
}