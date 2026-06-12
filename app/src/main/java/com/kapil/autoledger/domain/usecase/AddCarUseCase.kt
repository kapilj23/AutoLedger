package com.kapil.autoledger.domain.usecase

import com.kapil.autoledger.domain.model.Car
import com.kapil.autoledger.domain.repository.CarRepository
import javax.inject.Inject

class AddCarUseCase @Inject constructor(
    private val repository: CarRepository
) {
    suspend operator fun invoke(car: Car) = repository.insertCar(car)
}