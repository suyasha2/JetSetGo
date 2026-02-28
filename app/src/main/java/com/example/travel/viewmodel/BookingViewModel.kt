package com.example.travel.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.travel.model.BookingModel
import com.example.travel.repo.BookingRepo

class BookingViewModel(private val repo: BookingRepo) : ViewModel() {

    var fullName by mutableStateOf("")
    var phoneNumber by mutableStateOf("")
    var travelDate by mutableStateOf("")
    var guests by mutableStateOf("")
    var isSubmitting by mutableStateOf(false)

    fun confirmBooking(destination: String, price: String, onComplete: (Boolean) -> Unit) {
        if (fullName.isBlank() || phoneNumber.isBlank()) return
        isSubmitting = true

        val bookingData = BookingModel(
            destination = destination,
            userName = fullName,
            contact = phoneNumber,
            price = price
        )

        repo.confirmBooking(bookingData) { success, _ ->
            isSubmitting = false
            onComplete(success)
        }
    }
}