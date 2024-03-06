package kz.cicada.berkut.feature.maps.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kz.cicada.berkut.feature.maps.R
import kz.cicada.berkut.feature.maps.databinding.FragmentMapsBinding
import kz.cicada.berkut.lib.core.ui.base.fragment.BindingBaseFragment
import kz.cicada.berkut.lib.core.ui.navigation.FragmentTransition
import org.koin.androidx.viewmodel.ext.android.viewModel
import ua.naiksoftware.stomp.Stomp
import ua.naiksoftware.stomp.dto.StompHeader
import java.util.LinkedList


class MapsFragment : BindingBaseFragment<FragmentMapsBinding>(R.layout.fragment_maps),
    OnMapReadyCallback, FragmentTransition.LeftRight {
    override val viewModel: MapsViewModel by viewModel()
    private lateinit var mMap: GoogleMap
    override fun bindView(view: View) = FragmentMapsBinding.bind(view)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync { googleMap ->
            this@MapsFragment.onMapReady(googleMap)
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val sydney = LatLng(43.23, 76.88)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Almaty"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 12F))
    }


    fun configureWebSocket() {
        val mStompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP, "ws://berkut-mobile-app-dev.up.railway.app/ws-connection")

        mStompClient.connect()

        mStompClient.topic(
            "/topic/greetings",
            LinkedList<StompHeader>()
        ).subscribe { topicMessage ->
            Log.d(
                TAG,
                topicMessage.getPayload()
            )
        }

        mStompClient.send("/topic/hello-msg-mapping", "My first STOMP message!").subscribe()
    }
}