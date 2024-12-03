package com.example.tycoon_client.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tycoon_client.data.model.Region
import com.example.tycoon_client.data.repository.RegionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegionViewModel(private val repository: RegionRepository) : ViewModel() {
    private val _regions = MutableStateFlow<List<Region>>(emptyList())
    val regions: StateFlow<List<Region>> = _regions

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    init {
        fetchRegions()
    }

    private fun fetchRegions() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _regions.value = withContext(Dispatchers.IO) {
                    repository.getRegionsFromDb()
                }

                // Обновляем данные из API
                repository.getRegionsFromApi(
                    callback = { regions ->
                        _regions.value = regions
                        _isLoading.value = false
                    },
                    errorCallback = { errorMessage ->
                        Log.e("Debug", errorMessage)
                    }
                )
            } catch (e: Exception){
                e.message?.let { Log.e("Debug", it) }
            }

        }
    }
}