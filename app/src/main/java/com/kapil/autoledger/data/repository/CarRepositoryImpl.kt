package com.kapil.autoledger.data.repository

import com.kapil.autoledger.data.local.CarDao
import com.kapil.autoledger.data.local.entity.CarEntity
import com.kapil.autoledger.domain.model.Car
import com.kapil.autoledger.domain.repository.CarRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CarRepositoryImpl @Inject constructor(
    private val carDao: CarDao
) : CarRepository {

    override fun getAllCars(): Flow<List<Car>> {
        return carDao.getAllCars().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun getCarById(carId: Int): Flow<Car> {
        return carDao.getCarById(carId).map { it.toDomain() }
    }

    override suspend fun insertCar(car: Car) {
        carDao.insertCar(car.toEntity())
    }

    override suspend fun updateCar(car: Car) {
        carDao.updateCar(car.toEntity())
    }

    override suspend fun deleteCar(car: Car) {
        carDao.deleteCar(car.toEntity())
    }
}

// Extension functions — Entity to Domain
fun CarEntity.toDomain() = Car(
    id = id,
    name = name,
    model = model,
    year = year,
    fuelType = fuelType,
    createdAt = createdAt
)

// Domain to Entity
fun Car.toEntity() = CarEntity(
    id = id,
    name = name,
    model = model,
    year = year,
    fuelType = fuelType,
    createdAt = createdAt
)