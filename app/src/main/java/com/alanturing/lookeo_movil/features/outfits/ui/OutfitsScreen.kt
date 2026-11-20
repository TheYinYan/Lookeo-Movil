package com.alanturing.lookeo_movil.features.outfits.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alanturing.lookeo_movil.R
import com.alanturing.lookeo_movil.core.ui.components.LookeoBottomBar
import com.alanturing.lookeo_movil.core.ui.components.LookeoTopBar
import com.alanturing.lookeo_movil.core.ui.theme.LookeoGold
import com.alanturing.lookeo_movil.core.ui.theme.LookeoBlack
import com.alanturing.lookeo_movil.core.ui.theme.LookeoGray
import com.alanturing.lookeo_movil.core.ui.theme.LookeoWhite

@Composable
fun OutfitsScreen(
    onNavigateToHome: () -> Unit,
    onNavigateToProfile: () -> Unit,
    onNavigateToWardrobe: () -> Unit,
    onNavigateToFavorites: () -> Unit
) {
    Scaffold(
        topBar = {
            LookeoTopBar()
        },
        bottomBar = {
            LookeoBottomBar(
                selectedTab = "wardrobe",
                onNavigateToHome = onNavigateToHome,
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
            Text("Mis Outfits")

        }
    }
}
