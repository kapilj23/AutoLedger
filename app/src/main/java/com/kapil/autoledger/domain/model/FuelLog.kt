package com.kapil.autoledger.domain.model

data class FuelLog(
    val id: Int = 0,
    val carId: Int,
    val date: Long,
    val liters: Double,
    val pricePerLiter: Double,
    val totalCost: Double,
    val odometer: Int,
    val notes: String = ""
)