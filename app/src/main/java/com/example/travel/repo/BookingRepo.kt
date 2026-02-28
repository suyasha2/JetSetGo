package com.example.travel.repo

import com.example.travel.model.BookingModel

interface BookingRepo {
    fun confirmBooking(booking: BookingModel, callback: (Boolean, String) -> Unit)
}