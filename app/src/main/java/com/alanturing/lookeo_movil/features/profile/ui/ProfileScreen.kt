package com.alanturing.lookeo_movil.features.profile.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Logout
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.alanturing.lookeo_movil.core.ui.components.LookeoTopBar
import com.alanturing.lookeo_movil.core.ui.theme.LookeoBlack
import com.alanturing.lookeo_movil.core.ui.theme.LookeoGold
import com.alanturing.lookeo_movil.core.ui.theme.LookeoGray
import com.alanturing.lookeo_movil.core.ui.theme.LookeoWhite

@Composable
fun ProfileScreen(
    onNavigateToHome: () -> Unit,
    onNavigateToLogin: () -> Unit
) {
    var confirmLogout by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            LookeoTopBar(onBackClick = onNavigateToHome)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(LookeoBlack)
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Text(
                text = "Perfil de usuario",
                color = LookeoWhite
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { confirmLogout = true },
                colors = ButtonDefaults.buttonColors(
                    containerColor = LookeoGold,
                    contentColor = LookeoBlack
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Cerrar sesión")
            }
        }
    }

    if (confirmLogout) {
        AlertDialog(
            onDismissRequest = { confirmLogout = false },
            icon = {
                Box(
                    modifier = Modifier
                        .size(45.dp)
                        .background(LookeoBlack, CircleShape),
                    contentAlignment = Alignment.Center
                ){
                    Icon(
                        modifier = Modifier.size(22.dp),
                        imageVector = Icons.AutoMirrored.Outlined.Logout,
                        contentDescription = "cerrar sesión",
                        tint = LookeoGold
                    )
                }
            },
            title = {
                Text(
                    text = "¿Seguro que quieres cerrar sesión?",
                    style = MaterialTheme.typography.displayMedium,
//                    fontFamily = PlayfairDisplayRegular,
                    color = LookeoWhite,
                    textAlign = TextAlign.Center
                )
            },
            text = {
                Text(
                    text = "Tu armario y tus conjuntos seguirán aquí cuando vuelvas.",
                    style = MaterialTheme.typography.bodyMedium,
//                    fontFamily = InterRegular,
                    color = LookeoGray,
                    textAlign = TextAlign.Center
                )
            },
            confirmButton = {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        confirmLogout = false
                        onNavigateToLogin()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = LookeoGold,
                        contentColor = LookeoBlack
                    )
                ) {
                    Text("Cerrar sesión", style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold,)
                }
            }
        )
    }

}
