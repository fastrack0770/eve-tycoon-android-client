package com.example.tycoon_client.ui.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tycoon_client.data.model.Region
import com.example.tycoon_client.ui.viewmodel.RegionViewModel
import com.example.tycoon_client.ui.viewmodel.RegionViewModelFactory

@Composable
fun RegionListScreen(viewModel: RegionViewModel, modifier: Modifier = Modifier) {
    val regions = viewModel.regions.collectAsState(initial = emptyList()) // Собираем данные
    val isLoading = viewModel.isLoading.collectAsState(initial = true) // Отслеживаем состояние загрузки

    if (isLoading.value) {
        CircularProgressIndicator(modifier = modifier) // Отображаем индикатор загрузки
    } else {
        LazyColumn(modifier = modifier) {
            items(regions.value) { region ->
                RegionItem(region = region)
            }
        }
    }
}

@Composable
fun RegionItem(region: Region) {
    Text(text = "ID: ${region.id}, Name: ${region.name}")
}