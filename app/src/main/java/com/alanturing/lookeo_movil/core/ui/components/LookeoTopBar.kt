package com.alanturing.lookeo_movil.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alanturing.lookeo_movil.R
import com.alanturing.lookeo_movil.core.ui.theme.LookeoBlack
import com.alanturing.lookeo_movil.core.ui.theme.LookeoGold
import com.alanturing.lookeo_movil.core.ui.theme.LookeoWhite

@Composable
fun LookeoTopBar(
    onBackClick: (() -> Unit)? = null
){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(horizontal = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        if (onBackClick != null) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                    contentDescription = "Volver",
                    tint = LookeoWhite
                )
            }
        }
        Text(
            text = stringResource(id = R.string.app_logo),
            style = MaterialTheme.typography.displayMedium,
            //fontFamily = PlayfairDisplayRegular,
            //fontSize = 50.sp,
            color = LookeoGold,
        )
    }
}