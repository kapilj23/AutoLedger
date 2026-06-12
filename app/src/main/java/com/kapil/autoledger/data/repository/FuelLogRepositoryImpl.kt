package com.kapil.autoledger.data.repository

import com.kapil.autoledger.data.local.FuelLogDao
import com.kapil.autoledger.data.local.entity.FuelLogEntity
import com.kapil.autoledger.domain.model.FuelLog
import com.kapil.autoledger.domain.repository.FuelLogRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FuelLogRepositoryImpl @Inject constructor(
    private val fuelLogDao: FuelLogDao
) : FuelLogRepository {

    override fun getFuelLogsByCarId(carId: Int): Flow<List<FuelLog>> {
        return fuelLogDao.getFuelLogsByCarId(carId).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun getTotalExpenseByCarId(carId: Int): Flow<Double?> {
        return fuelLogDao.getTotalExpenseByCarId(carId)
    }

    override suspend fun insertFuelLog(fuelLog: FuelLog) {
        fuelLogDao.insertFuelLog(fuelLog.toEntity())
    }

    override suspend fun deleteFuelLog(fuelLog: FuelLog) {
        fuelLogDao.deleteFuelLog(fuelLog.toEntity())
    }
}

// Extension functions
fun FuelLogEntity.toDomain() = FuelLog(
    id = id,
    carId = carId,
    date = date,
    liters = liters,
    pricePerLiter = pricePerLiter,
    totalCost = totalCost,
    mileage = mileage,
    notes = notes
)

fun FuelLog.toEntity() = FuelLogEntity(
    id = id,
    carId = carId,
    date = date,
    liters = liters,
    pricePerLiter = pricePerLiter,
    totalCost = totalCost,
    mileage = mileage,
    notes = notes
)