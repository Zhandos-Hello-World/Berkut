package kz.cicada.berkut.feature.maps.presentation

import android.os.Bundle
import android.view.View
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kz.cicada.berkut.feature.maps.R
import kz.cicada.berkut.feature.maps.databinding.FragmentMapsBinding
import kz.cicada.berkut.lib.core.ui.base.BaseViewModel
import kz.cicada.berkut.lib.core.ui.base.fragment.BindingBaseFragment
import kz.cicada.berkut.lib.core.ui.navigation.FragmentTransition
import org.koin.androidx.viewmodel.ext.android.viewModel

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
}