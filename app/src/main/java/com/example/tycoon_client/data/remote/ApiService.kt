package com.example.tycoon_client.data.remote

import com.example.tycoon_client.data.model.Region
import retrofit2.http.GET
import retrofit2.Call

interface ApiService {
    @GET("market/regions")
    fun getRegionsData(): Call<List<Region>>
}