package com.alanturing.lookeo_movil.features.loading.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alanturing.lookeo_movil.R
import com.alanturing.lookeo_movil.core.ui.theme.LookeoGold
import com.alanturing.lookeo_movil.core.ui.theme.LookeoBlack
import com.alanturing.lookeo_movil.core.ui.theme.LookeoGray
import com.alanturing.lookeo_movil.core.ui.theme.LookeoWhite
import com.alanturing.lookeo_movil.core.ui.theme.InterRegular
import com.alanturing.lookeo_movil.core.ui.theme.PlayfairDisplayRegular
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

@Composable
fun LoadingScreen(
    onNavigateToLogin: () -> Unit
) {
    LaunchedEffect(Unit) {
        delay(5.seconds);
        onNavigateToLogin();
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LookeoBlack)
            .padding(horizontal = 24.dp)
            .padding(top = 40.dp),

        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(
            modifier = Modifier.height(110.dp)
        )
        Box(
            modifier = Modifier.size(200.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painterResource(R.drawable.ic_launcher_background),
                contentDescription = null,
                modifier = Modifier.size(200.dp)
            )
            Image(
                painterResource(R.drawable.ic_launcher_foreground),
                contentDescription = null,
                modifier = Modifier.size(200.dp)
            )
        }

        Text(
            text = stringResource(id = R.string.loading_lookeo),
            fontFamily = PlayfairDisplayRegular,
            fontSize = 72.sp,
            color = LookeoGold,
        )

        Spacer(
            modifier = Modifier.height(15.dp)
        )

        Text(
            text = stringResource(id = R.string.loading_slogan1),
            fontFamily = InterRegular,
            fontSize = 10.sp,
            color = LookeoGray,
        )

        Spacer(
            modifier = Modifier.height(70.dp)
        )

        CircularProgressIndicator(
            color = LookeoGold,
            modifier = Modifier.size(32.dp)
        )

        Spacer(
            modifier = Modifier.height(15.dp)
        )

        Text(
            text = stringResource(id = R.string.loading_cargando),
            fontFamily = InterRegular,
            fontSize = 12.sp,
            color = LookeoWhite,
        )

        Spacer(
            modifier = Modifier.height(70.dp)
        )

        Text(
            text = stringResource(id = R.string.loading_slogan2),
            fontFamily = InterRegular,
            fontSize = 12.sp,
            color = LookeoGray,
        )
    }
}