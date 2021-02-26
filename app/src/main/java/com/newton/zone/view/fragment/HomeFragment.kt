package com.newton.zone.view.fragment

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.newton.zone.extension.formatCoin
import com.newton.zone.extension.limit
import com.newton.zone.model.Business
import com.newton.zone.model.CLIENT
import com.newton.zone.model.LEAD
import com.newton.zone.model.Type
import com.newton.zone.view.fragment.HomeFragment.Constant.MAX_CHARACTER
import com.newton.zone.view.fragment.HomeFragment.Constant.REQUEST_LOCATION_PERMISSION
import com.newton.zone.view.fragment.HomeFragment.Constant.TIME_INTERVAL
import com.newton.zone.view.fragment.HomeFragment.Constant.ZOOM_IN
import com.newton.zone.view.viewmodel.BusinessViewModel
import com.newton.zone.view.viewmodel.StateAppComponentsViewModel
import com.newton.zone.view.viewmodel.VisualComponents
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import java.lang.Exception
import java.lang.IndexOutOfBoundsException
import java.util.*


class HomeFragment : Fragment() {

    private val appComponentsViewModel: StateAppComponentsViewModel by sharedViewModel()
    private val businessViewModel: BusinessViewModel by viewModel()
    private var markers = mutableListOf<Marker>()
    private var business = listOf<Business>()
    private lateinit var mGoogleMap: GoogleMap
    private lateinit var locationRequest: LocationRequest
    var location: Location? = null
    internal var currentMaker: Marker? = null
    private val mFusedLocationClient by lazy {
        LocationServices.getFusedLocationProviderClient(
            requireActivity()
        )
    }
    private lateinit var googleMap: GoogleMap
    private lateinit var mapFragment: SupportMapFragment
    private val navController by lazy { NavHostFragment.findNavController(this) }


    object Constant {
        const val REQUEST_LOCATION_PERMISSION: Int = 99
        const val TIME_INTERVAL: Long = 120000
        const val MAX_CHARACTER = 28
        const val ZOOM_IN = 9.0F
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(com.newton.zone.R.layout.fragment_home, container, false)
        appComponentsViewModel.havComponent = VisualComponents(true)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapFragment =
            childFragmentManager.findFragmentById(com.newton.zone.R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(callback)

        fragment_home_btn_register_lead.setOnClickListener { goToBusinessRegisterFragment() }
    }

    private fun goToBusinessRegisterFragment() {
        val direction = HomeFragmentDirections
            .actionHomeFragmentToFormBusinessRegisterFragment()
        navController.navigate(direction)
    }

    @SuppressLint("MissingPermission")
    private val callback = OnMapReadyCallback { googleMap ->
        this.googleMap = googleMap

        locationRequest = LocationRequest()
        locationRequest.interval = TIME_INTERVAL
        locationRequest.fastestInterval = TIME_INTERVAL
        locationRequest.priority = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
        configMap(googleMap)
        showBusinessPins(googleMap)
        onMarkerClickListener(googleMap)
        onBtnFilterLeadClickListener()
        onBtnFilterClientClickListener()
    }

    private fun onBtnFilterClientClickListener() {
        home_fragment_client_btn.setOnClickListener {
            setInvisibleAndShowAlso(@Type LEAD)
        }
    }

    private fun onBtnFilterLeadClickListener() {
        home_fragment_lead_btn.setOnClickListener {
            setInvisibleAndShowAlso(@Type CLIENT)
        }
    }

    private fun setInvisibleAndShowAlso(typeInvisible: String) {
        markers.forEach { marker ->
            business.forEach { business ->
                if (business.id == marker.tag && business.type == typeInvisible)
                    marker.isVisible = marker.isVisible != true
            }
        }
    }

    private fun onMarkerClickListener(googleMap: GoogleMap?) {
        googleMap?.setOnMarkerClickListener { marker ->
            if (marker.tag != null) {
                home_card_view.visibility = View.VISIBLE
                initViews(marker.tag)
            }
            false
        }
    }

    private fun initViews(tag: Any?) {
        businessViewModel.findById(tag as String).observe(viewLifecycleOwner, { business ->
            home_name_ec.text = business.name.toUpperCase(Locale.ROOT)
            txtTag.text = business.name[0].toString().toUpperCase(Locale.ROOT)
            home_address.text = business.address
            home_segment.text = business.segment
            home_tpv.text = business.tpv.formatCoin(requireContext())
            whenBusinessIsClient(business)
        })
    }

    private fun showBusinessPins(googleMap: GoogleMap) {
        businessViewModel.listAll().observe(viewLifecycleOwner) { listOfBusiness ->
            business = listOfBusiness
            for (business in listOfBusiness) {
                val latLng = getCoordinates(business.address)
                if (latLng != null)
                    if (business.type == @Type LEAD) {
                        markPin(
                            googleMap,
                            business,
                            latLng,
                            com.newton.zone.R.drawable.ic_green_pin
                        )
                    } else if (business.type == @Type CLIENT) {
                        markPin(
                            googleMap,
                            business,
                            latLng,
                            com.newton.zone.R.drawable.ic_purple_pin
                        )
                    }
            }
            if (listOfBusiness.isNotEmpty()) {
                try {
                    googleMap.animateCamera(
                        CameraUpdateFactory.newLatLngZoom(
                            getCoordinates(listOfBusiness.last().address),
                            ZOOM_IN
                        )
                    )
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    private fun markPin(
        map: GoogleMap,
        business: Business,
        latLng: LatLng,
        icImagePin: Int
    ) {
        val marker = map.addMarker(
            MarkerOptions()
                .title(business.name)
                .position(latLng)
                .icon(bitmapDescriptorFromVector(requireActivity(), icImagePin))
        )
        marker.tag = business.id
        markers.add(marker)
    }

    private fun configMap(googleMap: GoogleMap) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                mFusedLocationClient?.requestLocationUpdates(
                    locationRequest,
                    locationCallback,
                    Looper.myLooper()
                )
                googleMap.isMyLocationEnabled = true
            } else {
                checkLocationPermission()
            }
        } else {
            mFusedLocationClient?.requestLocationUpdates(
                locationRequest,
                locationCallback,
                Looper.myLooper()
            )
            googleMap.isMyLocationEnabled = true
        }
    }

    private fun checkLocationPermission() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    requireActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            ) {
                AlertDialog.Builder(requireContext())
                    .setTitle("Permissão Location Negada")
                    .setMessage("Este app precisa da Location permission, por favor aceite a funcionalidade location")
                    .setPositiveButton(
                        "OK"
                    ) { _, _ ->
                        ActivityCompat.requestPermissions(
                            requireActivity(),
                            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                            REQUEST_LOCATION_PERMISSION
                        )
                    }
                    .create()
                    .show()
            } else {
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    REQUEST_LOCATION_PERMISSION
                )
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_LOCATION_PERMISSION -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(
                            requireContext(),
                            Manifest.permission.ACCESS_FINE_LOCATION
                        ) == PackageManager.PERMISSION_GRANTED
                    ) {
                        mFusedLocationClient?.requestLocationUpdates(
                            locationRequest,
                            locationCallback,
                            Looper.myLooper()
                        )
                        mGoogleMap.isMyLocationEnabled = true
                    }

                } else {
                    Toast.makeText(requireContext(), "permission denied", Toast.LENGTH_LONG).show()
                }
                return
            }
        }
    }


    private fun getCoordinates(address: String): LatLng? {
        val location = Geocoder(requireContext())
        val fromLocationName: List<Address>
        try {
            fromLocationName = location.getFromLocationName(address, 1)
        } catch (error: Exception) {
            error.printStackTrace()
            return null
        }
        return try {
            LatLng(fromLocationName[0].latitude, fromLocationName[0].longitude)
        } catch (error: IndexOutOfBoundsException) {
            error.printStackTrace()
            null
        }
    }

    private var locationCallback: LocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            val locationList = locationResult.locations
            if (locationList.isNotEmpty()) {

                val location = locationList.last()
                Log.i("MapsActivity", "Location: " + location.latitude + " " + location.longitude)
                this@HomeFragment.location = location
                if (currentMaker != null) {
                    currentMaker?.remove()
                }

                val latLng = LatLng(location.latitude, location.longitude)
                val markerOptions = MarkerOptions()
                markerOptions.position(latLng)
                markerOptions.title("Você")
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA))
                currentMaker = googleMap.addMarker(markerOptions)

                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, ZOOM_IN))
            }
        }
    }

    private fun bitmapDescriptorFromVector(context: Context, vectorResId: Int): BitmapDescriptor? {
        val vectorDrawable = ContextCompat.getDrawable(context, vectorResId)
        vectorDrawable!!.setBounds(
            0,
            0,
            vectorDrawable.intrinsicWidth,
            vectorDrawable.intrinsicHeight
        )
        val bitmap = Bitmap.createBitmap(
            vectorDrawable.intrinsicWidth,
            vectorDrawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        vectorDrawable.draw(canvas)
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }

    private fun whenBusinessIsClient(business: Business) {
        if (business.type == @Type CLIENT) {
            home_business_id.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    com.newton.zone.R.color.purple_500
                )
            )
        } else {
            home_business_id.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    com.newton.zone.R.color.design_default_color_secondary_variant
                )
            )
        }
    }
}