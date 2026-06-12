package com.kapil.autoledger.domain.repository

import com.kapil.autoledger.domain.model.FuelLog
import kotlinx.coroutines.flow.Flow

interface FuelLogRepository {
    fun getFuelLogsByCarId(carId: Int): Flow<List<FuelLog>>
    fun getTotalExpenseByCarId(carId: Int): Flow<Double?>
    suspend fun insertFuelLog(fuelLog: FuelLog)
    suspend fun deleteFuelLog(fuelLog: FuelLog)
}