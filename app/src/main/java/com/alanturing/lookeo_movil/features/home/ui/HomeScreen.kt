package com.alanturing.lookeo_movil.features.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alanturing.lookeo_movil.core.data.local.UserEntity
import com.alanturing.lookeo_movil.core.ui.components.LookeoBottomBar
import com.alanturing.lookeo_movil.core.ui.components.LookeoTopBar
import com.alanturing.lookeo_movil.core.ui.theme.LookeoBlack
import com.alanturing.lookeo_movil.core.ui.theme.LookeoGold
import com.alanturing.lookeo_movil.core.ui.theme.LookeoGray
import com.alanturing.lookeo_movil.core.ui.theme.LookeoWhite
import com.alanturing.lookeo_movil.features.auth.ui.HealthViewModel
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Date
import java.util.Locale

@Composable
fun HomeScreen(
    onNavigateToProfile: () -> Unit,
    onNavigateToWardrobe: () -> Unit,
    onNavigateToOutfits: () -> Unit,
    onNavigateToFavorites: () -> Unit,
    healthViewModel: HealthViewModel = hiltViewModel() ,
    homeViewModel: HomeViewModel = hiltViewModel(),
) {
    val message by healthViewModel.message.collectAsStateWithLifecycle()
    val user by homeViewModel.valueUser.collectAsStateWithLifecycle(null)
    val date = remember {
        SimpleDateFormat("EEEE, d 'de' MMMM", Locale("es", "ES"))
            .format(Date())
            .replaceFirstChar { it.uppercase() }
    }
    LaunchedEffect(Unit) {
        healthViewModel.checkConnection()
    }

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

            Text(date, color = LookeoGold, style = MaterialTheme.typography.labelMedium, modifier = Modifier.padding(bottom = 12.dp))
            Text("Hola ${user?.name?:""}...", color = LookeoWhite, style = MaterialTheme.typography.displaySmall, modifier = Modifier.padding(bottom = 12.dp))
            Text("Tu conjunto del día, pensado para ti.", color = LookeoGray, style = MaterialTheme.typography.labelMedium, modifier = Modifier.padding(bottom = 12.dp))

            Text("¡Bienvenido a Lookeo!")
            Text(text = message)
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
