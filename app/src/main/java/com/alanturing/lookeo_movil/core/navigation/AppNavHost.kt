package com.alanturing.lookeo_movil.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alanturing.lookeo_movil.features.auth.ui.LoginScreen
import com.alanturing.lookeo_movil.features.auth.ui.RegisterScreen
import com.alanturing.lookeo_movil.features.home.ui.HomeScreen
import com.alanturing.lookeo_movil.features.profile.ui.ProfileScreen
import com.alanturing.lookeo_movil.features.outfits.ui.OutfitsScreen
import com.alanturing.lookeo_movil.features.wardrobe.ui.WardrobeScreen
import com.alanturing.lookeo_movil.features.favorites.ui.FavoritesScreen
import com.alanturing.lookeo_movil.features.loading.ui.LoadingScreen

@Composable
fun AppNavHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.Login,
        modifier = modifier
    ) {

        composable<Routes.Register> {
            RegisterScreen (
                onNavigateToHome = {navController.navigate(Routes.Home)},

                onNavigateToLogin = {navController.navigate(Routes.Login)}
            )
        }

        composable<Routes.Login> {
            LoginScreen (
                onNavigateToHome = {navController.navigate(Routes.Home)},
                onNavigateToRegister = {navController.navigate(Routes.Register)}
            )
        }

        composable<Routes.Home> {
            HomeScreen(
                onNavigateToProfile = { navController.navigate(Routes.Profile) },
                onNavigateToWardrobe = { navController.navigate(Routes.Wardrobe) },
                onNavigateToOutfits = { navController.navigate(Routes.Outfits) },
                onNavigateToFavorites = { navController.navigate(Routes.Favorites) }
            )
        }

        composable<Routes.Profile> {
            ProfileScreen (
                onNavigateToHome = {navController.navigate(Routes.Home)},
                onNavigateToLogin = {navController.navigate(Routes.Login)}
            )
        }

        composable<Routes.Wardrobe> {
            WardrobeScreen(
                onNavigateToHome = { navController.navigate(Routes.Home) },
                onNavigateToProfile = { navController.navigate(Routes.Profile) },
                onNavigateToOutfits = { navController.navigate(Routes.Outfits) },
                onNavigateToFavorites = { navController.navigate(Routes.Favorites) }
            )
        }

        composable<Routes.Outfits> {
            OutfitsScreen(
                onNavigateToHome = { navController.navigate(Routes.Home) },
                onNavigateToProfile = { navController.navigate(Routes.Profile) },
                onNavigateToWardrobe = { navController.navigate(Routes.Wardrobe) },
                onNavigateToFavorites = { navController.navigate(Routes.Favorites) }
            )
        }

        composable<Routes.Favorites> {
            FavoritesScreen(
                onNavigateToHome = { navController.navigate(Routes.Home) },
                onNavigateToProfile = { navController.navigate(Routes.Profile) },
                onNavigateToWardrobe = { navController.navigate(Routes.Wardrobe) },
                onNavigateToOutfits = { navController.navigate(Routes.Outfits) }
            )
        }
    }
}

