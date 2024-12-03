package com.example.tycoon_client.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "regions")
data class Region(
    @PrimaryKey val id: Int,
    val name: String
)