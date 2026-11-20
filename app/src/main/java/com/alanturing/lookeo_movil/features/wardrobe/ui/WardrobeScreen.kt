package com.alanturing.lookeo_movil.features.wardrobe.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.alanturing.lookeo_movil.core.ui.theme.LookeoGold
import com.alanturing.lookeo_movil.core.ui.theme.LookeoBlack

@Composable
fun WardrobeScreen(
    onNavigateToHome: () -> Unit,
    onNavigateToOutfits: () -> Unit,
    onNavigateToFavorites: () -> Unit,
    onNavigateToProfile: () -> Unit
) {
    Scaffold(
        topBar = {
            LookeoTopBar()
        },
        bottomBar = {
            LookeoBottomBar(
                selectedTab = "wardrobe",
                onNavigateToHome = onNavigateToHome,
                onNavigateToWardrobe = {},
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
            Text("¡Bienvenido al Armario!")

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
