package com.example.travel.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.travel.model.TravelPackage
import com.example.travel.repository.TravelRepository

class PackageViewModel : ViewModel() {
    private val repository = TravelRepository()
    var selectedPackage = mutableStateOf<TravelPackage?>(null)

    fun loadPackage(id: String) {
        selectedPackage.value = repository.getPackageById(id)
    }
}