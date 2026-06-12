package com.kapil.autoledger.domain.repository

import com.kapil.autoledger.domain.model.Car
import kotlinx.coroutines.flow.Flow

interface CarRepository {
    fun getAllCars(): Flow<List<Car>>
    fun getCarById(carId: Int): Flow<Car>
    suspend fun insertCar(car: Car)
    suspend fun updateCar(car: Car)
    suspend fun deleteCar(car: Car)
}