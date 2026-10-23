package com.alanturing.lookeo_movil.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alanturing.lookeo_movil.features.home.ui.HomeScreen
import com.alanturing.lookeo_movil.features.wardrobe.ui.WardrobeScreen
@Composable
fun AppNavHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.Home.route,
        modifier = modifier
    ) {
        composable(Routes.Home.route){
            HomeScreen({ navController.navigate(Routes.Wardrobe.route)})
        }
        composable(Routes.Wardrobe.route) {
            WardrobeScreen({ navController.navigate(Routes.Home.route)})
        }
    }
}
