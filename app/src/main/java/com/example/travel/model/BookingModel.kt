package com.example.travel.model

data class BookingModel(
    val id: String = "",
    val destination: String = "",
    val userName: String = "",
    val contact: String = "",
    val price: String = "",
    val currentUserId: String = "",
    val status: String = "Confirmed"
)