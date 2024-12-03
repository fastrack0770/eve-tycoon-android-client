package com.example.tycoon_client

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.tycoon_client.ui.theme.Tycoon_clientTheme
import com.example.tycoon_client.ui.viewmodel.RegionViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tycoon_client.data.local.AppDatabase
import com.example.tycoon_client.data.repository.RegionRepository
import com.example.tycoon_client.ui.screen.RegionListScreen
import com.example.tycoon_client.ui.viewmodel.RegionViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = AppDatabase.getInstance(applicationContext)
        val repository = RegionRepository(database.regionDao())

        enableEdgeToEdge()
        setContent {
            Tycoon_clientTheme {
                val factory = RegionViewModelFactory(repository)
                val viewModel: RegionViewModel = viewModel(factory = factory)
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    RegionListScreen(
                        viewModel = viewModel,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Tycoon_clientTheme {
        Greeting("Android")
    }
}