package com.example.travel.repo

import com.example.travel.R
import com.example.travel.model.DashboardPlace

interface DashboardRepo {
    fun getAllPlaces(): List<DashboardPlace>
    fun searchPlaces(query: String): List<DashboardPlace>
}