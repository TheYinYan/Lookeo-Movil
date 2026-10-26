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

@Composable
fun AppNavHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.Register.route,
        modifier = modifier
    ) {
        composable(Routes.Register.route) {
            RegisterScreen {
                navController.navigate(Routes.Login.route)
            }
        }

        composable(Routes.Login.route){
            LoginScreen {
                navController.navigate(Routes.Home.route)
            }
        }

        composable(Routes.Home.route) {
            HomeScreen(
                onNavigateToProfile = { navController.navigate(Routes.Profile.route) },
                onNavigateToWardrobe = { navController.navigate(Routes.Wardrobe.route) },
                onNavigateToOutfits = { navController.navigate(Routes.Outfits.route) },
                onNavigateToFavorites = { navController.navigate(Routes.Favorites.route) }
            )
        }

        composable(Routes.Profile.route) {
            ProfileScreen {
                navController.navigate(Routes.Home.route)
            }
        }

        composable(Routes.Wardrobe.route) {
            WardrobeScreen(
                onNavigateToHome = { navController.navigate(Routes.Home.route) },
                onNavigateToProfile = { navController.navigate(Routes.Profile.route) },
                onNavigateToOutfits = { navController.navigate(Routes.Outfits.route) },
                onNavigateToFavorites = { navController.navigate(Routes.Favorites.route) }
            )
        }

        composable(Routes.Outfits.route) {
            OutfitsScreen(
                onNavigateToHome = { navController.navigate(Routes.Home.route) },
                onNavigateToProfile = { navController.navigate(Routes.Profile.route) },
                onNavigateToWardrobe = { navController.navigate(Routes.Wardrobe.route) },
                onNavigateToFavorites = { navController.navigate(Routes.Favorites.route) }
            )
        }

        composable(Routes.Favorites.route) {
            FavoritesScreen(
                onNavigateToHome = { navController.navigate(Routes.Home.route) },
                onNavigateToProfile = { navController.navigate(Routes.Profile.route) },
                onNavigateToWardrobe = { navController.navigate(Routes.Wardrobe.route) },
                onNavigateToOutfits = { navController.navigate(Routes.Outfits.route) }
            )
        }
    }
}
