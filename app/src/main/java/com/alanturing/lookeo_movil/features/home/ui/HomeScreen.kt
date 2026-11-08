package com.alanturing.lookeo_movil.features.home.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alanturing.lookeo_movil.core.ui.theme.LookeoGold
import com.alanturing.lookeo_movil.core.ui.theme.LookeoBlack
import com.alanturing.lookeo_movil.core.ui.theme.LookeoGray
import com.alanturing.lookeo_movil.core.ui.theme.LookeoWhite
import com.alanturing.lookeo_movil.core.ui.theme.InterBold
import com.alanturing.lookeo_movil.core.ui.theme.PlayfairDisplayRegular

@Composable
fun HomeScreen(
    onNavigateToProfile: () -> Unit,
    onNavigateToWardrobe: () -> Unit,
    onNavigateToOutfits: () -> Unit,
    onNavigateToFavorites: () -> Unit
) {
    Column {
        Text("¡Bienvenido a Lookeo!")

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onNavigateToProfile,
            colors = ButtonDefaults.buttonColors(
                containerColor = LookeoGold,
                contentColor = LookeoBlack
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ir a mi cuenta")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onNavigateToWardrobe,
            colors = ButtonDefaults.buttonColors(
                containerColor = LookeoGold,
                contentColor = LookeoBlack
            ),
              modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ir a mi ropero")
        }

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

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onNavigateToFavorites,
            colors = ButtonDefaults.buttonColors(
                containerColor = LookeoGold,
                contentColor = LookeoBlack
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ir a mis favoritos")
        }
    }
}
