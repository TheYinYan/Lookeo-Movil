package com.alanturing.lookeo_movil.features.favorites.ui

import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alanturing.lookeo_movil.core.ui.components.LookeoBottomBar
import com.alanturing.lookeo_movil.core.ui.theme.LookeoGold
import com.alanturing.lookeo_movil.core.ui.theme.LookeoBlack
import com.alanturing.lookeo_movil.core.ui.theme.LookeoGray
import com.alanturing.lookeo_movil.core.ui.theme.LookeoWhite
import com.alanturing.lookeo_movil.core.ui.theme.InterBold
import com.alanturing.lookeo_movil.core.ui.theme.PlayfairDisplayRegular

@Composable
fun FavoritesScreen(
    onNavigateToProfile: () -> Unit,
    onNavigateToWardrobe: () -> Unit,
    onNavigateToOutfits: () -> Unit,
    onNavigateToHome: () -> Unit
) {
    Scaffold(
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
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Text("Mis Favoritos")

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
