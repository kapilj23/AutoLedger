package com.kapil.autoledger.domain.model

data class Car(
    val id: Int = 0,
    val name: String,
    val model: String,
    val year: Int,
    val fuelType: String,
    val createdAt: Long = System.currentTimeMillis()
)