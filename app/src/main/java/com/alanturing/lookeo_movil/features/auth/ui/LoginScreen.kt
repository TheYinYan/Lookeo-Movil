package com.alanturing.lookeo_movil.features.auth.ui

import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.alanturing.lookeo_movil.R
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

@Composable
fun LoginScreen(
    onNavigateToHome: () -> Unit,
    onNavigateToRegister: () -> Unit
) {
    val viewModel: LoginViewModel = viewModel()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(horizontal = 24.dp)
            .padding(top = 40.dp),

        verticalArrangement = Arrangement.Center
    ) {
        val date by viewModel.date.collectAsStateWithLifecycle()

        Image(
            painter = painterResource(id = R.drawable.login_img),
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
            color = Color.White,
            text = stringResource(id = R.string.login_welcome),
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        val textFieldsModifier = Modifier
            .fillMaxWidth()
            .padding(all = 8.dp)
        val customQuantityKeyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Text
        )

        Text(
            color = Color.Gray,
            text = stringResource(id = R.string.login_user_label)
        )

        TextField(
            modifier = textFieldsModifier,
            value = date.userName,
            onValueChange = { viewModel.updateName(it) },
            placeholder = { Text(stringResource(id = R.string.login_user_placeholder)) },
            leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) },
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.colors(
                unfocusedIndicatorColor = Color.Transparent,
                unfocusedContainerColor = Color(0xFF2C2C2C)
            )
        )

        Text(
            color = Color.Gray,
            text = stringResource(id = R.string.login_password_label)
        )

        TextField(
            modifier = textFieldsModifier,
            value = date.password,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation(),
            onValueChange = {
                viewModel.updatePassword(it)
            },
            placeholder = { Text(stringResource(id = R.string.login_password_placeholder)) },
            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.colors(
                unfocusedIndicatorColor = Color.Transparent,
                unfocusedContainerColor = Color(0xFF2C2C2C)
            )
        )

        ForgotPasswordLink()

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = onNavigateToHome,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFFC107),
                contentColor = Color.Black
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.login_button),
                fontWeight = FontWeight.Bold
            )
        }

        onRegister(onNavigateToRegister)
    }
}
@Composable
fun onRegister(onNavigateToRegister: () -> Unit) {
    Spacer(Modifier.height(16.dp))
    Row() {
        Text(
            text = stringResource(id = R.string.login_register_button) +" ",
            color = Color.Gray
        )
        Text(
            text = stringResource(id = R.string.login_register_button_link),
            color = Color(0xFFFFC107),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.clickable {
                onNavigateToRegister()
            }
        )
    }
}

@Composable
fun ForgotPasswordLink() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        Text(
            text = stringResource(id = R.string.login_forgot_password),
            color = Color.Gray,
            modifier = Modifier.clickable {
                // TODO Falta implementar Restaurar contraseña
            }
        )
    }
}