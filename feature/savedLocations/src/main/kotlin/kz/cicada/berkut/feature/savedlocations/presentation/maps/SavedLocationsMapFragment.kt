package kz.cicada.berkut.feature.savedlocations.presentation.maps

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kz.cicada.berkut.feature.savedlocations.R
import kz.cicada.berkut.feature.savedlocations.databinding.FragmentSavedLocationsBinding
import kz.cicada.berkut.lib.core.ui.base.fragment.BindingBaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

const val MAX_SIZE_RADIUS = 1_500.0
const val DEFAULT_SIZE_RADIUS = 750.0
const val MIN_SIZE_RADIUS = 100.0

private const val REQUEST_LOCATION_PERMISSION = 1

class SavedLocationsMapFragment(
    val launcher: SavedLocationsMapLauncher,
) :
    BindingBaseFragment<FragmentSavedLocationsBinding>(R.layout.fragment_saved_locations),
    OnMapReadyCallback {
    override val viewModel: SavedLocationsMapViewModel by viewModel()
    private lateinit var mMap: GoogleMap
    private var circleOptions: CircleOptions? = null
    var geoLocation = LatLng(43.23, 76.88)
    var radius = DEFAULT_SIZE_RADIUS

    override fun bindView(view: View) = FragmentSavedLocationsBinding.bind(view)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.back.setOnClickListener { viewModel.navigateUp() }

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync { googleMap ->
            this@SavedLocationsMapFragment.onMapReady(googleMap)
            enableMyLocation()
        }

        viewBinding.saveLocations.setOnClickListener {
            val circle = circleOptions
            if (circle != null) {
                val latitude = circle.center?.latitude ?: return@setOnClickListener
                val longitude = circle.center?.longitude ?: return@setOnClickListener
                viewModel.navigateToConfirm(
                    latitude = latitude,
                    longitude = longitude,
                    radius = circle.radius,
                )
            }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        mMap.addMarker(MarkerOptions().position(geoLocation).title("New Safe location"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(geoLocation, 12F))
        circleOptions = getCircleOptionsByDefault(geoLocation)
        circleOptions?.let(mMap::addCircle)

        setupLastLocations()

        googleMap.setOnMapClickListener { point ->
            mMap.clear()
            setupLastLocations()
            geoLocation = point
            mMap.addMarker(MarkerOptions().position(geoLocation).title("New Safe location"))
            circleOptions = getCircleOptionsByDefault(geoLocation)
            circleOptions?.let(mMap::addCircle)
            viewBinding.seekBarValue.text = "$radius m"
            viewBinding.centerInfo.text =
                "lat: ${geoLocation.latitude.toFloat()}, lon: ${geoLocation.longitude}"
        }


        viewBinding.radiusSeekbar.setOnSeekBarChangeListener(
            object : OnSeekBarChangeListener {
                override fun onStartTrackingTouch(seekBar: SeekBar?) = Unit
                override fun onStopTrackingTouch(seekBar: SeekBar?) = Unit

                @SuppressLint("SetTextI18n")
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean,
                ) {
                    radius = ((MAX_SIZE_RADIUS * progress) / 100.0)
                    viewBinding.seekBarValue.text = "${radius.toFloat()} m"
                    circleOptions?.radius(radius)
                    mMap.clear()
                    setupLastLocations()
                    mMap.addMarker(MarkerOptions().position(geoLocation).title("New Safe location"))

                    circleOptions?.let(mMap::addCircle)
                    viewBinding.centerInfo.text =
                        "lat: ${geoLocation.latitude.toFloat()}, lon: ${geoLocation.longitude}"
                }
            },
        )
    }

    private fun setupLastLocations() {
        launcher.list.forEach {
            val geoLocation = LatLng(it.latitude, it.longitude)
            mMap.addMarker(MarkerOptions().position(geoLocation).title(it.name))
            val circleOptions = getCircleOptionsByDefault(geoLocation)
            circleOptions.radius(it.radius)
            circleOptions.fillColor(0x30E33322)
            circleOptions.strokeColor(0x50E30200)
            circleOptions.let(mMap::addCircle)
        }
    }


    private fun getCircleOptionsByDefault(point: LatLng): CircleOptions {
        val circleOptions = CircleOptions()
        circleOptions.center(point)
        circleOptions.radius(radius)
        circleOptions.strokeColor(0x304651E3)
        circleOptions.fillColor(0x304651E3)
        circleOptions.strokeWidth(2f)
        return circleOptions
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

}