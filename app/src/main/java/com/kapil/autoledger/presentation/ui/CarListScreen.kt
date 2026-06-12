package com.kapil.autoledger.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.kapil.autoledger.domain.model.Car
import com.kapil.autoledger.presentation.viewmodel.CarViewModel

@Composable
fun CarListScreen(
    navController: NavController,
    viewModel: CarViewModel = hiltViewModel()
) {
    val cars by viewModel.cars.collectAsState()
    var showAddCarDialog by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showAddCarDialog = true },
                containerColor = Color(0xFF1A1A2E)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Car",
                    tint = Color.White
                )
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Text(
                text = "🚗 My Cars",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1A1A2E)
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (cars.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            imageVector = Icons.Default.DirectionsCar,
                            contentDescription = null,
                            modifier = Modifier.size(80.dp),
                            tint = Color.LightGray
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "No cars added yet",
                            color = Color.Gray,
                            fontSize = 16.sp
                        )
                        Text(
                            text = "Tap + to add your first car",
                            color = Color.LightGray,
                            fontSize = 14.sp
                        )
                    }
                }
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(cars) { car ->
                        CarCard(
                            car = car,
                            onClick = {
                                navController.navigate("car_detail/${car.id}")
                            },
                            onDelete = {
                                viewModel.deleteCar(car)
                            }
                        )
                    }
                }
            }
        }

        if (showAddCarDialog) {
            AddCarDialog(
                onDismiss = { showAddCarDialog = false },
                onAddCar = { car ->
                    viewModel.addCar(car)
                    showAddCarDialog = false
                }
            )
        }
    }
}

@Composable
fun CarCard(
    car: Car,
    onClick: () -> Unit,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF1A1A2E)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.DirectionsCar,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(36.dp)
                )
                Column {
                    Text(
                        text = car.name,
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "${car.model} • ${car.year}",
                        color = Color.LightGray,
                        fontSize = 13.sp
                    )
                    Text(
                        text = car.fuelType,
                        color = Color(0xFF4CAF50),
                        fontSize = 12.sp
                    )
                }
            }
            IconButton(onClick = onDelete) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete",
                    tint = Color.Red
                )
            }
        }
    }
}

@Composable
fun AddCarDialog(
    onDismiss: () -> Unit,
    onAddCar: (Car) -> Unit
) {
    var name by remember { mutableStateOf("") }
    var model by remember { mutableStateOf("") }
    var year by remember { mutableStateOf("") }
    var selectedFuelType by remember { mutableStateOf("Petrol") }

    val fuelTypes = listOf("Petrol", "Diesel", "CNG", "Electric")

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "Add New Car",
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Car Name") },
                    placeholder = { Text("My Honda City") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = model,
                    onValueChange = { model = it },
                    label = { Text("Model") },
                    placeholder = { Text("Honda City") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = year,
                    onValueChange = { year = it },
                    label = { Text("Year") },
                    placeholder = { Text("2020") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                Text("Fuel Type", fontWeight = FontWeight.Medium)
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    fuelTypes.forEach { fuelType ->
                        FilterChip(
                            selected = selectedFuelType == fuelType,
                            onClick = { selectedFuelType = fuelType },
                            label = { Text(fuelType, fontSize = 12.sp) }
                        )
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (name.isNotBlank() && model.isNotBlank() && year.isNotBlank()) {
                        onAddCar(
                            Car(
                                name = name,
                                model = model,
                                year = year.toIntOrNull() ?: 2020,
                                fuelType = selectedFuelType
                            )
                        )
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF1A1A2E)
                )
            ) {
                Text("Add Car")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}