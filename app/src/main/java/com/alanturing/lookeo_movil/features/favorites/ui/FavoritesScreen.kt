package com.alanturing.lookeo_movil.features.favorites.ui

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
import androidx.compose.material3.MaterialTheme
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
import com.alanturing.lookeo_movil.core.ui.theme.LookeoGray
import com.alanturing.lookeo_movil.core.ui.theme.LookeoWhite

@Composable
fun FavoritesScreen(
    onNavigateToProfile: () -> Unit,
    onNavigateToWardrobe: () -> Unit,
    onNavigateToOutfits: () -> Unit,
    onNavigateToHome: () -> Unit
) {
    Scaffold(
        topBar = {
            LookeoTopBar()
        },
        bottomBar = {
            LookeoBottomBar(
                selectedTab = "favorites",
                onNavigateToHome = onNavigateToHome,
                onNavigateToWardrobe = onNavigateToWardrobe,
                onNavigateToFavorites = {},
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
            Text("TU SELECCIÓN PERSONAL", color = LookeoGold, style = MaterialTheme.typography.labelMedium, modifier = Modifier.padding(bottom = 12.dp))
            Text("Favoritos", color = LookeoWhite, style = MaterialTheme.typography.displaySmall, modifier = Modifier.padding(bottom = 12.dp))
            Text("Tus prendas y looks preferidos, a mano.", color = LookeoGray, style = MaterialTheme.typography.labelMedium, modifier = Modifier.padding(bottom = 12.dp))

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
