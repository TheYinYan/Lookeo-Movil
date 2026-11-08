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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp
import com.alanturing.lookeo_movil.core.ui.theme.InterBold
import com.alanturing.lookeo_movil.core.ui.theme.LookeoGold
import com.alanturing.lookeo_movil.core.ui.theme.LookeoBlack
import com.alanturing.lookeo_movil.core.ui.theme.LookeoGray
import com.alanturing.lookeo_movil.core.ui.theme.LookeoWhite
import com.alanturing.lookeo_movil.core.ui.theme.InterRegular
import com.alanturing.lookeo_movil.core.ui.theme.LookeoDarkGrey
import com.alanturing.lookeo_movil.core.ui.theme.PlayfairDisplayRegular

@Composable
fun LoginScreen(
    onNavigateToHome: () -> Unit,
    onNavigateToRegister: () -> Unit
) {
    val viewModel: LoginViewModel = viewModel()

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(72.dp)
                    .background(LookeoBlack),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.app_logo),
                    fontFamily = PlayfairDisplayRegular,
                    fontSize = 50.sp,
                    color = LookeoGold,
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(LookeoBlack)
                .padding(innerPadding)
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
                text = stringResource(id = R.string.login_welcome),
                fontFamily = InterRegular,
                fontSize = 18.sp,
                color = LookeoWhite
            )

            Spacer(modifier = Modifier.height(16.dp))

            val textFieldsModifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
            val customQuantityKeyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text
            )

            Text(
                text = stringResource(id = R.string.login_user_label),
                fontFamily = InterRegular,
                fontSize = 15.sp,
                color = LookeoGray
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
                    unfocusedContainerColor = LookeoDarkGrey
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(id = R.string.login_password_label),
                fontFamily = InterRegular,
                fontSize = 15.sp,
                color = LookeoGray
            )

            var passwordVisible by remember { mutableStateOf(false) }

            TextField(
                modifier = textFieldsModifier
                    .padding(bottom = 8.dp),
                value = date.password,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = if (passwordVisible) KeyboardType.Text else KeyboardType.Password
                ),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                onValueChange = {
                    viewModel.updatePassword(it)
                },
                placeholder = { Text(stringResource(id = R.string.login_password_placeholder)) },
                leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
                trailingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                            contentDescription = null
                        )
                    }
                },
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.colors(
                    unfocusedIndicatorColor = Color.Transparent,
                    unfocusedContainerColor = LookeoDarkGrey
                )
            )

            ForgotPasswordLink()

            Spacer(Modifier.height(26.dp))

            Button(
                onClick = onNavigateToHome,
                colors = ButtonDefaults.buttonColors(
                    containerColor = LookeoGold,
                    contentColor = LookeoBlack
                ),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.login_button),
                    fontFamily = InterBold,
                    fontSize = 15.sp,
                    color = LookeoBlack
                )
            }

            onRegister(onNavigateToRegister)
        }
    }
}

@Composable
fun onRegister(onNavigateToRegister: () -> Unit) {
    Spacer(Modifier.height(16.dp))
    Row (
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(
            text = stringResource(id = R.string.login_register_button) +" ",
            fontFamily = InterRegular,
            fontSize = 15.sp,
            color = LookeoGray
        )
        Text(
            text = stringResource(id = R.string.login_register_button_link),
            fontFamily = InterBold,
            fontSize = 15.sp,
            color = LookeoGold,
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
            fontFamily = InterRegular,
            fontSize = 14.sp,
            color = LookeoGray,
            modifier = Modifier.clickable {
                // TODO Falta implementar Restaurar contraseña
            }
        )
    }
}