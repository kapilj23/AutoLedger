package com.kapil.autoledger.di

import android.content.Context
import androidx.room.Room
import com.kapil.autoledger.data.local.AutoLedgerDatabase
import com.kapil.autoledger.data.local.CarDao
import com.kapil.autoledger.data.local.FuelLogDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AutoLedgerDatabase {
        return Room.databaseBuilder(
            context,
            AutoLedgerDatabase::class.java,
            "autoledger_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideCarDao(database: AutoLedgerDatabase): CarDao {
        return database.carDao()
    }

    @Provides
    @Singleton
    fun provideFuelLogDao(database: AutoLedgerDatabase): FuelLogDao {
        return database.fuelLogDao()
    }
}