package com.newton.zone.view.viewmodel

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.location.Address
import android.location.Geocoder
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.newton.zone.model.Business
import com.newton.zone.repository.BusinessRepository


class BusinessViewModel(private val repository: BusinessRepository) : ViewModel() {

    fun insert(business: Business) = repository.insert(business)

    fun update(business: Business) = repository.update(business)

    fun remove(business: Business) = repository.remove(business)

    fun listAll() = repository.listAll()

    fun findById(businessId: String) = repository.findById(businessId)

    fun findBusinessFilter(query: String) = repository.findBusinessFilter(query)

    fun checkBusinessReturned(): LiveData<MutableList<Business>> = repository.businessReturned

    fun bitmapDescriptorFromVector(context: Context, vectorResId: Int): BitmapDescriptor? {
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

    fun getCoordinates(address: String, context: Context): LatLng? {
        val location = Geocoder(context)
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
}