package com.kapil.autoledger.data.local

import androidx.room.*
import com.kapil.autoledger.data.local.entity.FuelLogEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FuelLogDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFuelLog(fuelLog: FuelLogEntity)

    @Delete
    suspend fun deleteFuelLog(fuelLog: FuelLogEntity)

    @Query("SELECT * FROM fuel_logs WHERE carId = :carId ORDER BY date DESC")
    fun getFuelLogsByCarId(carId: Int): Flow<List<FuelLogEntity>>

    @Query("SELECT SUM(totalCost) FROM fuel_logs WHERE carId = :carId")
    fun getTotalExpenseByCarId(carId: Int): Flow<Double?>

}