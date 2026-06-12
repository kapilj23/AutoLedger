package com.kapil.autoledger.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "fuel_logs",
    foreignKeys = [
        ForeignKey(
            entity = CarEntity::class,
            parentColumns = ["id"],
            childColumns = ["carId"],
            onDelete = ForeignKey.CASCADE  // car delete ho toh logs bhi delete
        )
    ]
)
data class FuelLogEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val carId: Int,           // konsi car ka log hai
    val date: Long,           // kab petrol dala
    val liters: Double,       // kitna petrol dala
    val pricePerLiter: Double, // price per liter
    val totalCost: Double,    // liters * pricePerLiter
    val odometer: Int,        // current km reading
    val notes: String = ""    // optional notes
)