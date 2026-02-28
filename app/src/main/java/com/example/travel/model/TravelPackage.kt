package com.example.travel.model

data class TravelPackage(
    val id: String,
    val name: String,
    val location: String,
    val price: String,
    val duration: String,
    val packages: List<String>,
    val itinerary: List<ItineraryStep>
)

data class ItineraryStep(
    val day: String,
    val title: String,
    val detail: String
)
