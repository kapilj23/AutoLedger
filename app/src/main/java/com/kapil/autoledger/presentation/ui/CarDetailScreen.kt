package com.kapil.autoledger.presentation.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.LocalGasStation
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
import com.kapil.autoledger.domain.model.FuelLog
import com.kapil.autoledger.presentation.viewmodel.CarViewModel
import com.kapil.autoledger.presentation.viewmodel.FuelLogViewModel
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarDetailScreen(
    carId: Int,
    navController: NavController,
    viewModel: FuelLogViewModel = hiltViewModel(),
    carViewModel: CarViewModel = hiltViewModel()
) {
    val cars by carViewModel.cars.collectAsState()
    val car = cars.find { it.id == carId }

    val fuelLogs by viewModel.fuelLogs.collectAsState()
    val totalExpense by viewModel.totalExpense.collectAsState()

    LaunchedEffect(carId) {
        viewModel.selectCar(carId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = car?.name ?: "Car Detail",
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
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate("add_fuel_log/$carId")
                },
                containerColor = Color(0xFF1A1A2E)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Fuel Log",
                    tint = Color.White
                )
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Car Info Card
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF1A1A2E)
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = car?.name ?: "",
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "${car?.model} • ${car?.year}",
                            color = Color.LightGray,
                            fontSize = 14.sp
                        )
                        Text(
                            text = car?.fuelType ?: "",
                            color = Color(0xFF4CAF50),
                            fontSize = 14.sp
                        )
                    }
                }
            }

            // Total Expense Card
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF2A2A3E)
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = "💰 Total Fuel Expense",
                            color = Color.White,
                            fontSize = 14.sp
                        )
                        Text(
                            text = "₹ ${String.format("%.2f", totalExpense ?: 0.0)}",
                            color = Color(0xFF4CAF50),
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "${fuelLogs.size} fuel logs",
                            color = Color.LightGray,
                            fontSize = 12.sp
                        )
                    }
                }
            }

            item {
                val avgMileage = if (fuelLogs.isNotEmpty()) {
                    fuelLogs.map { it.mileage }.average()
                } else null

                avgMileage?.let {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFF2A2A3E)
                        )
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text(
                                text = "🚀 Average Mileage",
                                color = Color.White,
                                fontSize = 14.sp
                            )
                            Text(
                                text = "${String.format("%.2f", it)} km/L",
                                color = Color(0xFFFFEB3B),
                                fontSize = 28.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "Based on ${fuelLogs.size} fuel logs",
                                color = Color.LightGray,
                                fontSize = 12.sp
                            )
                        }
                    }
                }
            }

            // Fuel Logs Header
            item {
                Text(
                    text = "⛽ Fuel Logs",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1A1A2E)
                )
            }

            // Empty State
            if (fuelLogs.isEmpty()) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(32.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                imageVector = Icons.Default.LocalGasStation,
                                contentDescription = null,
                                modifier = Modifier.size(60.dp),
                                tint = Color.LightGray
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "No fuel logs yet",
                                color = Color.Gray
                            )
                            Text(
                                text = "Tap + to add fuel log",
                                color = Color.LightGray,
                                fontSize = 13.sp
                            )
                        }
                    }
                }
            }

            // Fuel Log Cards
            items(fuelLogs) { fuelLog ->
                FuelLogCard(
                    fuelLog = fuelLog,
                    onDelete = { viewModel.deleteFuelLog(fuelLog) }
                )
            }
        }
    }
}

@Composable
fun FuelLogCard(
    fuelLog: FuelLog,
    onDelete: () -> Unit
) {
    val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
    val date = dateFormat.format(Date(fuelLog.date))

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF5F5F5)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = date,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1A1A2E)
                )
                Text(
                    text = "⛽ ${fuelLog.liters}L @ ₹${fuelLog.pricePerLiter}/L",
                    fontSize = 13.sp,
                    color = Color.DarkGray
                )
                Text(
                    text = "🚀 ${fuelLog.mileage} km/L",  // ← odometer hata, mileage add
                    fontSize = 13.sp,
                    color = Color.Gray
                )
                if (fuelLog.notes.isNotBlank()) {
                    Text(
                        text = fuelLog.notes,
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
            }
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "₹${String.format("%.2f", fuelLog.totalCost)}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1A1A2E)
                )
                IconButton(
                    onClick = onDelete,
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete",
                        tint = Color.Red,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
    }
}