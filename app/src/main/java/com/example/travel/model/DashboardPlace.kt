package com.example.travel.model

data class DashboardPlace(
    val id: String = "",
    val title: String = "",
    val location: String = "",
    val price: String = "",
    val rating: String = "",
    val image: Int,
    val isFavorite: Boolean = false
)