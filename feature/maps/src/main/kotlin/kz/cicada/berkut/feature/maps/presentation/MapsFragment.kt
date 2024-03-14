package kz.cicada.berkut.feature.maps.presentation

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.launch
import kz.cicada.berkut.feature.maps.R
import kz.cicada.berkut.feature.maps.databinding.FragmentMapsBinding
import kz.cicada.berkut.feature.socketconnection.presentation.SocketViewModel
import kz.cicada.berkut.lib.core.ui.base.fragment.BindingBaseFragment
import kz.cicada.berkut.lib.core.ui.navigation.FragmentTransition
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val REQUEST_LOCATION_PERMISSION = 1

class MapsFragment : BindingBaseFragment<FragmentMapsBinding>(R.layout.fragment_maps),
    OnMapReadyCallback, FragmentTransition.LeftRight {
    override val viewModel: MapsViewModel by viewModel()
    val almaty = LatLng(43.23, 76.88)


    private val socketViewModel: SocketViewModel by viewModel()
    private lateinit var mMap: GoogleMap
    override fun bindView(view: View) = FragmentMapsBinding.bind(view)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync { googleMap ->
            this@MapsFragment.onMapReady(googleMap)
            enableMyLocation()
        }
        observeCurrentLocation()
        setListeners()

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.addMarker(
            MarkerOptions()
                .position(almaty)
                .title("Marker in Almaty")
        )

        drawCircle(almaty)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(almaty, 12F))
    }

    private fun setListeners() {
        with(viewBinding) {
            eye.setOnClickListener {
                mMap.moveCamera(
                    CameraUpdateFactory.newLatLngZoom(
                        almaty,
                        12F
                    )
                )
            }
            zoom.setOnClickListener {
                mMap.moveCamera(
                    CameraUpdateFactory.newLatLngZoom(almaty, mMap.cameraPosition.zoom + 1F)
                )
            }
            unzoom.setOnClickListener {
                mMap.moveCamera(
                    CameraUpdateFactory.newLatLngZoom(almaty, mMap.cameraPosition.zoom - 1F)
                )
            }
        }
    }


    private fun observeCurrentLocation() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.connectionWebSocket.collect {
                    if (it) {
                        socketViewModel.start()
                    }
                }
            }
        }
        socketViewModel.liveChatState.observe(viewLifecycleOwner) {
            Log.d("liveChatState", it.toString())
        }
    }

    @SuppressLint("MissingPermission")
    private fun enableMyLocation() {
        if (isPermissionGranted()) {
            mMap.isMyLocationEnabled = true
        } else {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_LOCATION_PERMISSION,
            )
        }
    }

    private fun isPermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION,
        ) == PackageManager.PERMISSION_GRANTED
    }


    private fun drawCircle(point: LatLng) {
        val circleOptions = CircleOptions()
        circleOptions.center(point)
        circleOptions.radius(15.0)
        circleOptions.strokeColor(0x304651E3)
        circleOptions.fillColor(0x304651E3)
        circleOptions.strokeWidth(2f)
        mMap.addCircle(circleOptions)
    }


    data class Place(
        val name: String,
        val latLng: LatLng,
        val address: LatLng,
        val rating: Float
    )
}