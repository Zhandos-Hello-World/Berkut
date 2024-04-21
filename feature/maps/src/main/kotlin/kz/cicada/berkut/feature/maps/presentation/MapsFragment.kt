package kz.cicada.berkut.feature.maps.presentation

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.gson.Gson
import kotlinx.coroutines.launch
import kz.cicada.berkut.feature.maps.R
import kz.cicada.berkut.feature.maps.databinding.FragmentMapsBinding
import kz.cicada.berkut.feature.maps.presentation.socket.MapsSocketBehavior
import kz.cicada.berkut.feature.maps.presentation.socket.MapsSocketModel
import kz.cicada.berkut.feature.savedlocations.presentation.maps.SavedLocationsMapLauncher
import kz.cicada.berkut.feature.socketconnection.presentation.SocketLauncher
import kz.cicada.berkut.feature.socketconnection.presentation.SocketViewModel
import kz.cicada.berkut.lib.core.data.network.UserType
import kz.cicada.berkut.lib.core.ui.base.fragment.BindingBaseFragment
import kz.cicada.berkut.lib.core.ui.navigation.FragmentTransition
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

private const val REQUEST_LOCATION_PERMISSION = 1

class MapsFragment : BindingBaseFragment<FragmentMapsBinding>(R.layout.fragment_maps),
    OnMapReadyCallback, FragmentTransition.LeftRight {
    override val viewModel: MapsViewModel by viewModel()
    private var geoSecondLocation: LatLng? = null
    private var savedLocations: List<SavedLocationsMapLauncher.SafeLocation> = emptyList()

    private val socketViewModel: SocketViewModel by viewModel(
        parameters = {
            parametersOf(
                SocketLauncher(
                    behavior = MapsSocketBehavior(),
                ),
            )
        },
    )
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
        observeSafeLocations()
        setListeners()

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
    }

    private fun setListeners() {
        with(viewBinding) {
            zoom.setOnClickListener {
                geoSecondLocation?.let { geoSecondLocation ->
                    mMap.animateCamera(
                        CameraUpdateFactory.newLatLngZoom(
                            geoSecondLocation, mMap.cameraPosition.zoom + 1F
                        )
                    )
                }
            }
            unzoom.setOnClickListener {
                geoSecondLocation?.let { geoSecondLocation ->
                    mMap.animateCamera(
                        CameraUpdateFactory.newLatLngZoom(
                            geoSecondLocation, mMap.cameraPosition.zoom - 1F
                        )
                    )
                }
            }
        }
    }


    private fun observeCurrentLocation() {
        socketViewModel.start()
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.role.collect {
                    viewBinding.sos.isVisible = it == UserType.CHILD
                    viewBinding.sos.setOnClickListener {
                        viewModel.onSOSClick()
                    }
                }
            }
        }
        socketViewModel.messageState.observe(viewLifecycleOwner) {
            try {
                val model = Gson().fromJson(it, MapsSocketModel::class.java)
                val latitude = model.latitude?.toDouble() ?: 0.0
                val longitude = model.longitude?.toDouble() ?: 0.0

                mMap.clear()
                drawSafeLocations()
                geoSecondLocation = LatLng(latitude, longitude)
                geoSecondLocation?.let { geoSecondLocation ->
                    mMap.addMarker(
                        MarkerOptions().position(geoSecondLocation).title(model.username)
                    )
                }

            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }

    private fun observeSafeLocations() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.savedLocations.collect {
                    savedLocations = it
                    drawSafeLocations()
                }
            }
        }
    }

    private fun drawSafeLocations() {
        savedLocations.forEach {
            val geoLocation = LatLng(it.latitude, it.longitude)
            mMap.addMarker(MarkerOptions().position(geoLocation).title(it.name))
            val circleOptions = getCircleOptionsByDefault(geoLocation)
            circleOptions.radius(it.radius)
            circleOptions.fillColor(0x30E33322)
            circleOptions.strokeColor(0x50E30200)
            circleOptions.let(mMap::addCircle)
        }
    }

    @SuppressLint("MissingPermission")
    private fun enableMyLocation() {
        if (isPermissionGranted()) {
            mMap.isMyLocationEnabled = true
            LocationServices.getFusedLocationProviderClient(requireContext()).lastLocation.addOnSuccessListener { location: Location? ->
                location?.let {
                    geoSecondLocation = LatLng(
                        location.latitude,
                        location.longitude,
                    )
                    geoSecondLocation?.let { geoSecondLocation ->
                        drawCircle(geoSecondLocation)
                        mMap.animateCamera(
                            CameraUpdateFactory.newLatLngZoom(
                                geoSecondLocation, 12F
                            )
                        )
                    }
                }
            }
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

    private fun getCircleOptionsByDefault(point: LatLng): CircleOptions {
        val circleOptions = CircleOptions()
        circleOptions.center(point)
        circleOptions.strokeColor(0x304651E3)
        circleOptions.fillColor(0x304651E3)
        circleOptions.strokeWidth(2f)
        return circleOptions
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
}