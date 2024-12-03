package com.example.tycoon_client.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tycoon_client.data.repository.RegionRepository

class RegionViewModelFactory(
    private val repository: RegionRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegionViewModel::class.java)) {
            return RegionViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}