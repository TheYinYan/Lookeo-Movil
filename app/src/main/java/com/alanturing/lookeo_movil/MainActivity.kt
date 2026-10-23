package com.alanturing.lookeo_movil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.alanturing.lookeo_movil.core.navigation.AppNavHost
import com.alanturing.lookeo_movil.core.ui.theme.LookeoMovilTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LookeoMovilTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                   AppNavHost(
                       modifier = Modifier.padding(innerPadding),
                   )
                }
            }
        }
    }
}
