package com.kapil.autoledger.presentation.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "car_list"
    ) {
        composable("car_list") {
            CarListScreen(navController = navController)
        }
        composable(
            route = "car_detail/{carId}",
            arguments = listOf(
                navArgument("carId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val carId = backStackEntry.arguments?.getInt("carId") ?: 0
            CarDetailScreen(
                carId = carId,
                navController = navController
            )
        }
        composable(
            route = "add_fuel_log/{carId}",
            arguments = listOf(
                navArgument("carId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val carId = backStackEntry.arguments?.getInt("carId") ?: 0
            AddFuelLogScreen(
                carId = carId,
                navController = navController
            )
        }
    }
}