package com.example.travel.repo

import com.example.travel.R
import com.example.travel.model.DashboardPlace
interface DashboardRepository {
    fun getAllPlaces(): List<DashboardPlace>
    fun updateFavoriteStatus(placeId: String, isFav: Boolean)
    fun searchPlaces(query: String): List<DashboardPlace>
}

object DashboardDataHolder {
    var placesList = listOf(
        DashboardPlace("mountain", "Mount Everest", "Solukhumbu", "Rs. 1,50,000", "4.9", R.drawable.mountain, false),
        DashboardPlace("pokhara", "Pokhara Lakeside", "Pokhara", "Rs. 35,000", "4.7", R.drawable.pokhara, false),
        DashboardPlace("chitwan", "Chitwan Park", "Chitwan", "Rs. 28,000", "4.6", R.drawable.chitwan, false),
        DashboardPlace("pashupati", "Pashupati Temple", "Kathmandu", "Rs. 5,000", "4.8", R.drawable.pashupatinath, false)
    )
}

class DashboardRepositoryImpl : DashboardRepository {
    override fun getAllPlaces() = DashboardDataHolder.placesList

    override fun updateFavoriteStatus(placeId: String, isFav: Boolean) {
        // Repo main list update
        DashboardDataHolder.placesList = DashboardDataHolder.placesList.map {
            if (it.id == placeId) it.copy(isFavorite = isFav) else it
        }
    }

    override fun searchPlaces(query: String) = DashboardDataHolder.placesList.filter {
        it.title.contains(query, true)
    }
}