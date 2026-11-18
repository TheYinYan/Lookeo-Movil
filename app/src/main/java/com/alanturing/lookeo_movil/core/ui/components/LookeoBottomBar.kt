package com.alanturing.lookeo_movil.core.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material.icons.outlined.SpaceDashboard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.alanturing.lookeo_movil.core.ui.theme.LookeoBlack
import com.alanturing.lookeo_movil.core.ui.theme.LookeoGold
import com.alanturing.lookeo_movil.core.ui.theme.LookeoGray

@Composable
fun LookeoBottomBar(
    selectedTab: String,
    onNavigateToHome: () -> Unit,
    onNavigateToWardrobe: () -> Unit,
    onNavigateToFavorites: () -> Unit,
    onNavigateToProfile: () -> Unit
) {
    val itemColors = NavigationBarItemDefaults.colors(
        selectedIconColor = LookeoGold,
        selectedTextColor = LookeoGold,
        indicatorColor = Color.Transparent,
        unselectedIconColor = LookeoGray,
        unselectedTextColor = LookeoGray
    )

    Column {
        HorizontalDivider(thickness = 0.5.dp, color = LookeoGray.copy(alpha = 0.3f))
        NavigationBar(
            containerColor = LookeoBlack,
            tonalElevation = 0.dp
        ) {
            NavigationBarItem(
                selected = selectedTab == "home",
                onClick = onNavigateToHome,
                colors = itemColors,
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.Home,
                        contentDescription = "Inicio"
                    )
                },
                label = { Text("Inicio") }
            )
            NavigationBarItem(
                selected = selectedTab == "wardrobe",
                onClick = onNavigateToWardrobe,
                colors = itemColors,
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.SpaceDashboard,
                        contentDescription = "Ropero"
                    )
                },
                label = { Text("Ropero") }
            )
            NavigationBarItem(
                selected = selectedTab == "favorites",
                onClick = onNavigateToFavorites,
                colors = itemColors,
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.FavoriteBorder,
                        contentDescription = "Favoritos"
                    )
                },
                label = { Text("Favoritos") }
            )
            NavigationBarItem(
                selected = selectedTab == "profile",
                onClick = onNavigateToProfile,
                colors = itemColors,
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.PersonOutline,
                        contentDescription = "Perfil"
                    )
                },
                label = { Text("Perfil") }
            )
        }
    }
}
