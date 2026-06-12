package com.kapil.autoledger.presentation.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.kapil.autoledger.domain.model.FuelLog
import com.kapil.autoledger.presentation.viewmodel.FuelLogViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddFuelLogScreen(
    carId: Int,
    navController: NavController,
    viewModel: FuelLogViewModel = hiltViewModel()
) {
    var liters by remember { mutableStateOf("") }
    var pricePerLiter by remember { mutableStateOf("") }
    var odometer by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Add Fuel Log",
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF1A1A2E),
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "⛽ Fuel Details",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1A1A2E)
            )

            // Liters
            OutlinedTextField(
                value = liters,
                onValueChange = { liters = it },
                label = { Text("Liters Filled") },
                placeholder = { Text("35.5") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal
                ),
                modifier = Modifier.fillMaxWidth(),
                suffix = { Text("L") }
            )

            // Price per liter
            OutlinedTextField(
                value = pricePerLiter,
                onValueChange = { pricePerLiter = it },
                label = { Text("Price per Liter") },
                placeholder = { Text("96.50") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal
                ),
                modifier = Modifier.fillMaxWidth(),
                prefix = { Text("₹") }
            )

            // Total Cost — Auto calculated
            val totalCost = (liters.toDoubleOrNull() ?: 0.0) *
                    (pricePerLiter.toDoubleOrNull() ?: 0.0)

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF1A1A2E)
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Total Cost",
                        color = Color.White,
                        fontSize = 16.sp
                    )
                    Text(
                        text = "₹ ${String.format("%.2f", totalCost)}",
                        color = Color(0xFF4CAF50),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            // Odometer
            OutlinedTextField(
                value = odometer,
                onValueChange = { odometer = it },
                label = { Text("Odometer Reading") },
                placeholder = { Text("45230") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                modifier = Modifier.fillMaxWidth(),
                suffix = { Text("km") }
            )

            // Notes
            OutlinedTextField(
                value = notes,
                onValueChange = { notes = it },
                label = { Text("Notes (Optional)") },
                placeholder = { Text("Full tank, highway trip...") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Save Button
            Button(
                onClick = {
                    val litersVal = liters.toDoubleOrNull()
                    val priceVal = pricePerLiter.toDoubleOrNull()
                    val odometerVal = odometer.toIntOrNull()

                    if (litersVal != null && priceVal != null && odometerVal != null) {
                        viewModel.addFuelLog(
                            FuelLog(
                                carId = carId,
                                date = System.currentTimeMillis(),
                                liters = litersVal,
                                pricePerLiter = priceVal,
                                totalCost = litersVal * priceVal,
                                odometer = odometerVal,
                                notes = notes
                            )
                        )
                        navController.popBackStack()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF1A1A2E)
                )
            ) {
                Text(
                    text = "Save Fuel Log",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}