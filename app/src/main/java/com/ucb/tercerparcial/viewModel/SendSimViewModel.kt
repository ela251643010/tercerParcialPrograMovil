package com.ucb.tercerparcial.viewModel


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class SendSimViewModel : ViewModel() {
    val phone = mutableStateOf("")
    val lat = mutableStateOf<Double?>(null)
    val lng = mutableStateOf<Double?>(null)

    fun updatePhone(value: String) {
        phone.value = value
    }

    fun updateLocation(lat: Double, lng: Double) {
        this.lat.value = lat
        this.lng.value = lng
    }

    fun isValid(): Boolean {
        return phone.value.isNotEmpty() && lat.value != null && lng.value != null
    }
}
