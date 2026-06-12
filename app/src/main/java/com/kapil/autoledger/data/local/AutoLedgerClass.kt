package com.kapil.autoledger.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kapil.autoledger.data.local.entity.CarEntity
import com.kapil.autoledger.data.local.entity.FuelLogEntity

@Database(
    entities = [
        CarEntity::class,
        FuelLogEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class AutoLedgerDatabase : RoomDatabase() {
    abstract fun carDao(): CarDao
    abstract fun fuelLogDao(): FuelLogDao
}