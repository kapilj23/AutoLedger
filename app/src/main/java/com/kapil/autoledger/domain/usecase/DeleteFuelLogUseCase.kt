package com.kapil.autoledger.domain.usecase

import com.kapil.autoledger.domain.model.FuelLog
import com.kapil.autoledger.domain.repository.FuelLogRepository
import javax.inject.Inject

class DeleteFuelLogUseCase @Inject constructor(
    private val repository: FuelLogRepository
) {
    suspend operator fun invoke(fuelLog: FuelLog) = repository.deleteFuelLog(fuelLog)
}