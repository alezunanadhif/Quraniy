package com.yus.quran.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class SharedViewModel(context: Context) : ViewModel() {
    private val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    private val _getLasKnownLocation = MutableLiveData<List<Double>>()
    val getLastKnownLocation: LiveData<List<Double>> = _getLasKnownLocation

    @SuppressLint("MissingPermission")
    fun requestLocationUpdates() {
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            location?.let {
                val latLng = listOf(it.latitude, it.longitude)
                _getLasKnownLocation.value = latLng
            }
        }

        fusedLocationClient.lastLocation.addOnFailureListener { exception ->
            Log.e("SharedViewModel", "requestLocationUpdates: " + exception.localizedMessage)
        }
    }
}