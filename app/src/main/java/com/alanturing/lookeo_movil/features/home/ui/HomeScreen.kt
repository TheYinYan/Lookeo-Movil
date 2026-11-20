package com.alanturing.lookeo_movil.features.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material.icons.outlined.SpaceDashboard
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alanturing.lookeo_movil.R
import com.alanturing.lookeo_movil.core.ui.components.LookeoBottomBar
import com.alanturing.lookeo_movil.core.ui.components.LookeoTopBar
import com.alanturing.lookeo_movil.core.ui.theme.LookeoBlack
import com.alanturing.lookeo_movil.core.ui.theme.LookeoGold

@Composable
fun HomeScreen(
    onNavigateToProfile: () -> Unit,
    onNavigateToWardrobe: () -> Unit,
    onNavigateToOutfits: () -> Unit,
    onNavigateToFavorites: () -> Unit
) {
    Scaffold(
        topBar = {
            LookeoTopBar()
        },
        bottomBar = {
            LookeoBottomBar(
                selectedTab = "home",
                onNavigateToHome = {},
                onNavigateToWardrobe = onNavigateToWardrobe,
                onNavigateToFavorites = onNavigateToFavorites,
                onNavigateToProfile = onNavigateToProfile
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(LookeoBlack)
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Text("¡Bienvenido a Lookeo!")

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onNavigateToOutfits,
                colors = ButtonDefaults.buttonColors(
                    containerColor = LookeoGold,
                    contentColor = LookeoBlack
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Ir a mis outfits")
            }
        }
    }
}
