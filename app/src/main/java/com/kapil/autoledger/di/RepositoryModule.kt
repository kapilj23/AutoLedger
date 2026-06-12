package com.kapil.autoledger.di

import com.kapil.autoledger.data.repository.CarRepositoryImpl
import com.kapil.autoledger.data.repository.FuelLogRepositoryImpl
import com.kapil.autoledger.domain.repository.CarRepository
import com.kapil.autoledger.domain.repository.FuelLogRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCarRepository(
        impl: CarRepositoryImpl
    ): CarRepository

    @Binds
    @Singleton
    abstract fun bindFuelLogRepository(
        impl: FuelLogRepositoryImpl
    ): FuelLogRepository
}