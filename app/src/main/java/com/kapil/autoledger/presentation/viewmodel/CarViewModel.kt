package com.kapil.autoledger.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kapil.autoledger.domain.model.Car
import com.kapil.autoledger.domain.usecase.AddCarUseCase
import com.kapil.autoledger.domain.usecase.DeleteCarUseCase
import com.kapil.autoledger.domain.usecase.GetAllCarsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarViewModel @Inject constructor(
    private val getAllCarsUseCase: GetAllCarsUseCase,
    private val addCarUseCase: AddCarUseCase,
    private val deleteCarUseCase: DeleteCarUseCase
) : ViewModel() {

    val cars = getAllCarsUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun addCar(car: Car) {
        viewModelScope.launch {
            addCarUseCase(car)
        }
    }

    fun deleteCar(car: Car) {
        viewModelScope.launch {
            deleteCarUseCase(car)
        }
    }
}