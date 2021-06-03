package com.platzi.conf.view.ui.location


import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*

import com.platzi.conf.R
import com.platzi.conf.model.Location

/**
 * A simple [Fragment] subclass.
 */
class LocationFragment : Fragment(),OnMapReadyCallback,GoogleMap.OnMarkerClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {return inflater.inflate(R.layout.fragment_location, container, false) }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment =childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }


    override fun onMapReady(googleMap: GoogleMap?) {

        val location = Location()
        val zoom =16f
        val centerMap = LatLng(location.latitude,location.longitude)

        googleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(centerMap,zoom))

        val centerMark =  LatLng(location.latitude,location.longitude)
        val markerOptions = MarkerOptions()

        markerOptions.position(centerMark)
        markerOptions.title("Platzi conf 2020")

        val bitmapDraw = context?.applicationContext?.let { ContextCompat.getDrawable(it,R.drawable.logo_platzi) as BitmapDrawable }
        val smallMarker = Bitmap.createScaledBitmap(bitmapDraw?.bitmap,150,150,false)

        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(smallMarker))
        googleMap?.addMarker(markerOptions)


        //aqui mandara automaticamente al onmarket click la mnandara a traer
        googleMap?.setOnMarkerClickListener (this)

        googleMap?.setMapStyle(MapStyleOptions.loadRawResourceStyle(context,R.raw.custom_map))

    }

    override fun onMarkerClick(p0: Marker?): Boolean {
            findNavController().navigate(R.id.locationDetailFragmentDialog)
            return true
    }


}
