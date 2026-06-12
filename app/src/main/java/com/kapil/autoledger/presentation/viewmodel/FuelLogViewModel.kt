package com.kapil.autoledger.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kapil.autoledger.domain.model.FuelLog
import com.kapil.autoledger.domain.usecase.AddFuelLogUseCase
import com.kapil.autoledger.domain.usecase.DeleteFuelLogUseCase
import com.kapil.autoledger.domain.usecase.GetFuelLogsUseCase
import com.kapil.autoledger.domain.usecase.GetTotalExpenseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FuelLogViewModel @Inject constructor(
    private val getFuelLogsUseCase: GetFuelLogsUseCase,
    private val addFuelLogUseCase: AddFuelLogUseCase,
    private val deleteFuelLogUseCase: DeleteFuelLogUseCase,
    private val getTotalExpenseUseCase: GetTotalExpenseUseCase
) : ViewModel() {

    private val _selectedCarId = MutableStateFlow(0)

    val fuelLogs = _selectedCarId
        .flatMapLatest { carId ->
            getFuelLogsUseCase(carId)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val totalExpense = _selectedCarId
        .flatMapLatest { carId ->
            getTotalExpenseUseCase(carId)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0.0
        )

    fun selectCar(carId: Int) {
        _selectedCarId.value = carId
    }

    fun addFuelLog(fuelLog: FuelLog) {
        viewModelScope.launch {
            addFuelLogUseCase(fuelLog)
        }
    }

    fun deleteFuelLog(fuelLog: FuelLog) {
        viewModelScope.launch {
            deleteFuelLogUseCase(fuelLog)
        }
    }
}