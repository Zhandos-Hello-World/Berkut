package kz.cicada.berkut.feature.socketconnection.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ua.naiksoftware.stomp.Stomp
import ua.naiksoftware.stomp.StompClient
import ua.naiksoftware.stomp.dto.LifecycleEvent
import ua.naiksoftware.stomp.dto.StompMessage
import ua.naiksoftware.stomp.provider.OkHttpConnectionProvider.TAG


class MainViewModel {
    companion object {
        const val SOCKET_URL = "ws://berkut-mobile-app-dev.up.railway.app/ws-connection"
        const val CHAT_TOPIC = "/user/${1}/geo-data"
    }

    private var mStompClient: StompClient? = null
    private var compositeDisposable: CompositeDisposable? = null

    private val _chatState = MutableLiveData<String?>()
    val liveChatState: LiveData<String?> = _chatState

    init {
        mStompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP, SOCKET_URL/*, headerMap*/)
            .withServerHeartbeat(30000)
        resetSubscriptions()
        initChat()
    }

    private fun initChat() {
        resetSubscriptions()

        if (mStompClient != null) {
            val topicSubscribe =
                mStompClient!!.topic(CHAT_TOPIC).subscribeOn(Schedulers.io(), false)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ topicMessage: StompMessage ->
                        Log.d(TAG, topicMessage.payload)
                        addMessage(topicMessage.payload)
                    }, {
                        Log.e(TAG, "Error!", it)
                    })

            val lifecycleSubscribe = mStompClient!!.lifecycle().subscribeOn(Schedulers.io(), false)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { lifecycleEvent: LifecycleEvent ->
                    when (lifecycleEvent.type!!) {
                        LifecycleEvent.Type.OPENED -> Log.d(TAG, "Stomp connection opened")
                        LifecycleEvent.Type.ERROR -> Log.e(TAG, "Error", lifecycleEvent.exception)
                        LifecycleEvent.Type.FAILED_SERVER_HEARTBEAT, LifecycleEvent.Type.CLOSED -> {
                            Log.d(TAG, "Stomp connection closed")
                        }
                    }
                }

            compositeDisposable!!.add(lifecycleSubscribe)
            compositeDisposable!!.add(topicSubscribe)

            if (!mStompClient!!.isConnected) {
                mStompClient!!.connect()
            }


        } else {
            Log.e(TAG, "mStompClient is null!")
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

    fun onCleared() {
        mStompClient?.disconnect()
        compositeDisposable?.dispose()
    }
}