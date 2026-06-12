package com.kapil.autoledger.domain.usecase

import com.kapil.autoledger.domain.repository.CarRepository
import javax.inject.Inject

class GetAllCarsUseCase @Inject constructor(
    private val repository: CarRepository
) {
    operator fun invoke() = repository.getAllCars()
}