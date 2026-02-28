package com.example.travel.viewmodel

import androidx.lifecycle.ViewModel
import com.example.travel.model.DashboardPlace
import com.example.travel.repo.DashboardRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DashboardViewModel(private val repo: DashboardRepository) : ViewModel() {

    private val _places = MutableStateFlow(repo.getAllPlaces())
    val places: StateFlow<List<DashboardPlace>> = _places.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    fun toggleFavorite(placeId: String) {
        val currentList = _places.value.toMutableList()
        val index = currentList.indexOfFirst { it.id == placeId }

        if (index != -1) {
            val place = currentList[index]
            val updatedStatus = !place.isFavorite

            val updatedPlace = place.copy(isFavorite = updatedStatus)
            currentList[index] = updatedPlace
            _places.value = currentList

            repo.updateFavoriteStatus(placeId, updatedStatus)
        }
    }
//Search//
    fun onSearchChange(newQuery: String) {
        _searchQuery.value = newQuery
        _places.value = if (newQuery.isBlank()) {
            repo.getAllPlaces()
        } else {
            repo.searchPlaces(newQuery)
        }
    }
}