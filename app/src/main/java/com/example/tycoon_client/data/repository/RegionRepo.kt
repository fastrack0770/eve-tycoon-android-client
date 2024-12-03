package com.example.tycoon_client.data.repository

import android.util.Log
import com.example.tycoon_client.data.local.RegionDao
import com.example.tycoon_client.data.model.Region
import com.example.tycoon_client.data.remote.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegionRepository(private val dao: RegionDao) {
    private val api = RetrofitInstance.api

    fun getRegionsFromApi(callback: (List<Region>) -> Unit, errorCallback: (String) -> Unit) {
        Log.e("Debug", "Start to fetch")

        // Асинхронный запрос через enqueue
        api.getRegionsData().enqueue(object : Callback<List<Region>> {
            override fun onResponse(call: Call<List<Region>>, response: Response<List<Region>>) {
                if (response.isSuccessful) {
                    Log.e("Debug", "Succeed to fetch")
                    response.body()?.let {
                        // Сохраняем в базу данных
                        CoroutineScope(Dispatchers.IO).launch {
                            dao.insertRegions(it)  // Сохраняем в базу данных в фоновом потоке
                        }

                        // Возвращаем данные через callback
                        callback(it)
                    }
                } else {
                    Log.e("Debug", "Failed to fetch, error code: ${response.code()}")
                    errorCallback("Failed to fetch data. Error code: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<Region>>, t: Throwable) {
                Log.e("Debug", "Error: ${t.message}")
                errorCallback("Failed to fetch data. Error: ${t.message}")
            }
        })
    }

    suspend fun getRegionsFromDb(): List<Region> {
        return dao.getAllRegions()  // Извлечение данных из базы
    }
}