package com.alanturing.lookeo_movil.features.auth.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alanturing.lookeo_movil.R
import com.alanturing.lookeo_movil.core.ui.theme.InterBold
import com.alanturing.lookeo_movil.core.ui.theme.LookeoGold
import com.alanturing.lookeo_movil.core.ui.theme.LookeoBlack
import com.alanturing.lookeo_movil.core.ui.theme.LookeoGray
import com.alanturing.lookeo_movil.core.ui.theme.LookeoWhite
import com.alanturing.lookeo_movil.core.ui.theme.InterRegular
import com.alanturing.lookeo_movil.core.ui.theme.PlayfairDisplayRegular

@Composable
fun RegisterScreen(
    onNavigateToHome: () -> Unit,
    onNavigateToLogin: () -> Unit,
) {
    val viewModel: RegisterViewModel = viewModel()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LookeoBlack)
            .padding(horizontal = 24.dp)
            .padding(top = 40.dp),

        verticalArrangement = Arrangement.Center
    ) {
        val date by viewModel.date.collectAsStateWithLifecycle()

        Image(
            painter = painterResource(id = R.drawable.register_img),
            contentDescription = "Imagen de bienvenida",
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(260.dp)
                .clip(RoundedCornerShape(16.dp))
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = stringResource(id = R.string.register_welcome),
            fontFamily = InterBold,
            fontSize = 15.sp,
            color = LookeoGray
        )

        Spacer(modifier = Modifier.height(16.dp))

        val textFieldsModifier = Modifier
            .fillMaxWidth()
            .padding(all = 8.dp)
        val customQuantityKeyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Text
        )
        Text(
            color = LookeoGray,
            text = stringResource(id = R.string.register_user_label)
        )
        TextField(
            modifier = textFieldsModifier,
            value = date.userName,
            onValueChange = { viewModel.createName(it) },
            placeholder = { Text(stringResource(id = R.string.register_user_placeholder)) },
            leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) },
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.colors(
                unfocusedIndicatorColor = Color.Transparent,
                unfocusedContainerColor = Color(0xFF2C2C2C)
            )
        )

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = onNavigateToHome,
            colors = ButtonDefaults.buttonColors(
                containerColor = LookeoGold,
                contentColor = LookeoBlack
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.register_button),
                fontFamily = InterBold,
                fontSize = 12.sp,
                color = LookeoBlack
            )
        }

        onLogin(onNavigateToLogin)
    }
}
@Composable

fun onLogin(onNavigateToLogin: () -> Unit) {
    Spacer(Modifier.height(16.dp))
    Row() {
        Text(
            text = stringResource(id = R.string.register_login_button) +" ",
            fontFamily = InterBold,
            fontSize = 10.sp,
            color = LookeoGray
        )
        Text(
            text = stringResource(id = R.string.register_login_button_link),
            fontFamily = InterBold,
            fontSize = 10.sp,
            color = LookeoGold
        )
    }
}