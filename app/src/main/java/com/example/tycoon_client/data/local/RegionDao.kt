package com.example.tycoon_client.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tycoon_client.data.model.Region

@Dao
interface RegionDao {
    @Query("SELECT * FROM regions")
    suspend fun getAllRegions(): List<Region>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRegions(regions: List<Region>)
}