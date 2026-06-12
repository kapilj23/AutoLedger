package com.kapil.autoledger.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cars")
data class CarEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,        // "My Honda City"
    val model: String,       // "Honda City"
    val year: Int,           // 2020
    val fuelType: String,    // "Petrol" / "Diesel" / "CNG"
    val createdAt: Long = System.currentTimeMillis()
)