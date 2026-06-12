package com.kapil.autoledger.domain.usecase

import com.kapil.autoledger.domain.repository.FuelLogRepository
import javax.inject.Inject

class GetFuelLogsUseCase @Inject constructor(
    private val repository: FuelLogRepository
) {
    operator fun invoke(carId: Int) = repository.getFuelLogsByCarId(carId)
}