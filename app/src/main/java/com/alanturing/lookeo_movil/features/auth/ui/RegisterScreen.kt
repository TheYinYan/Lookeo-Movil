package com.alanturing.lookeo_movil.features.auth.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alanturing.lookeo_movil.R
import com.alanturing.lookeo_movil.core.ui.theme.LookeoGold
import com.alanturing.lookeo_movil.core.ui.theme.LookeoBlack
import com.alanturing.lookeo_movil.core.ui.theme.LookeoGray
import com.alanturing.lookeo_movil.core.ui.theme.LookeoDarkGrey
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.material3.CircularProgressIndicator
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel

@Composable
fun RegisterScreen(
    onNavigateToLogin: () -> Unit,
    onNavigateToHome: () -> Unit
) {
    val viewModel: RegisterViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

	val errorMessage = uiState.errorMessageResId?.let { stringResource(id = it) }
		?: uiState.errorMessage

    LaunchedEffect(uiState.isSuccess) {
        if (uiState.isSuccess) {
            onNavigateToHome()
            viewModel.resetSuccessState()
        }
    }

    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(LookeoBlack),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.app_logo),
                    style = MaterialTheme.typography.displayMedium,
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
                .padding(top = 10.dp)
                .imePadding()
                .verticalScroll(rememberScrollState()),

            verticalArrangement = Arrangement.Center
        ) {

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

			val textFieldsModifier = Modifier
				.fillMaxWidth()
				.padding(top = 8.dp)

            Text(
                text = stringResource(id = R.string.register_user_label),
                style = MaterialTheme.typography.bodyMedium,
                color = LookeoGray,
            )
            TextField(
				modifier = textFieldsModifier,
				value = uiState.username,
				onValueChange = { viewModel.onUsernameChanged(it) },
				placeholder = { Text(stringResource(id = R.string.register_user_placeholder)) },
				leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) },
				shape = RoundedCornerShape(12.dp),
				colors = TextFieldDefaults.colors(
					unfocusedIndicatorColor = Color.Transparent,
					unfocusedContainerColor = LookeoDarkGrey
				)
			)
			Spacer(Modifier.height(8.dp))

			Text(
				text = stringResource(id = R.string.register_email_label),
				style = MaterialTheme.typography.bodyMedium,
				color = LookeoGray,
			)
			TextField(
				modifier = textFieldsModifier,
				value = uiState.email,
				onValueChange = { viewModel.onEmailChanged(it) },
				placeholder = { Text(stringResource(id = R.string.register_email_placeholder)) },
				leadingIcon = { Icon(Icons.Default.Mail, contentDescription = null) },
				shape = RoundedCornerShape(12.dp),
				colors = TextFieldDefaults.colors(
					unfocusedIndicatorColor = Color.Transparent,
					unfocusedContainerColor = LookeoDarkGrey
				)
			)
			Spacer(Modifier.height(8.dp))


			Text(
				text = stringResource(id = R.string.register_password_label),
				style = MaterialTheme.typography.bodyMedium,
				color = LookeoGray,
			)
			TextField(
				modifier = textFieldsModifier,
				value = uiState.password,
				onValueChange = { viewModel.onPasswordChanged(it) },
				keyboardOptions = KeyboardOptions.Default.copy(
					keyboardType = if (passwordVisible) KeyboardType.Text else KeyboardType.Password
				),
				visualTransformation = if (passwordVisible) {
					VisualTransformation.None
				} else {
					PasswordVisualTransformation()
				},
				trailingIcon = {
					IconButton(onClick = { passwordVisible = !passwordVisible }) {
						Icon(
							if (passwordVisible) Icons.Filled.Visibility
							else Icons.Filled.VisibilityOff,
							contentDescription = null
						)
					}
				},
				placeholder = { Text(stringResource(id = R.string.register_password_placeholder)) },
				leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
				shape = RoundedCornerShape(12.dp),
				colors = TextFieldDefaults.colors(
					unfocusedIndicatorColor = Color.Transparent,
					unfocusedContainerColor = LookeoDarkGrey
				)
			)
			Spacer(Modifier.height(8.dp))

			Text(
				text = stringResource(id = R.string.register_password_confirm_label),
				style = MaterialTheme.typography.bodyMedium,
				color = LookeoGray,
			)
			TextField(
				modifier = textFieldsModifier,
				value = uiState.confirmPassword,
				onValueChange = { viewModel.onConfirmPasswordChanged(it) },
				keyboardOptions = KeyboardOptions.Default.copy(
					keyboardType = if (confirmPasswordVisible) KeyboardType.Text else KeyboardType.Password
				),
				visualTransformation = if (confirmPasswordVisible) {
					VisualTransformation.None
				} else {
					PasswordVisualTransformation()
				},
				trailingIcon = {
					IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
						Icon(
							if (confirmPasswordVisible) Icons.Filled.Visibility
							else Icons.Filled.VisibilityOff,
							contentDescription = null
						)
					}
				},
				placeholder = { Text(stringResource(id = R.string.register_password_confirm_placeholder)) },
				leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
				shape = RoundedCornerShape(12.dp),
				colors = TextFieldDefaults.colors(
					unfocusedIndicatorColor = Color.Transparent,
					unfocusedContainerColor = LookeoDarkGrey
				)
			)
			Text(
				text = stringResource(id = R.string.register_password_characters),
				style = MaterialTheme.typography.bodyMedium,
				color = LookeoGray,
			)
			Spacer(Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
					checked = uiState.acceptedTerms,
					onCheckedChange = { viewModel.onAcceptedTermsChanged(it) }
				)
				Text(
					text = stringResource(id = R.string.register_terms),
					style = MaterialTheme.typography.bodyMedium,
					color = LookeoGray
				)
            }

			if (errorMessage != null) {
				Text(
					text = errorMessage,
					color = Color.Red,
					style = MaterialTheme.typography.bodyMedium
				)
				Spacer(Modifier.height(8.dp))
			}

            Button(
				enabled = !uiState.isLoading,
				onClick = { viewModel.register() },  // NO onNavigateToHome
				colors = ButtonDefaults.buttonColors(
					containerColor = LookeoGold,
					contentColor = LookeoBlack
				),
				shape = RoundedCornerShape(8.dp),
				modifier = Modifier.fillMaxWidth()
			) {
				if (uiState.isLoading) {
					CircularProgressIndicator(
						modifier = Modifier.height(24.dp),
						color = LookeoBlack,
						strokeWidth = 2.dp
					)
				} else {
					Text(
						text = stringResource(id = R.string.register_button),
						style = MaterialTheme.typography.bodyMedium,
						fontWeight = FontWeight.Bold,
						color = LookeoBlack
					)
				}
			}

            OnLogin(onNavigateToLogin)
        }
    }
}


@Composable
fun OnLogin(onNavigateToLogin: () -> Unit) {
    Spacer(Modifier.height(16.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = R.string.register_login_button) + " ",
            style = MaterialTheme.typography.bodyMedium,
            color = LookeoGray
        )
        Text(
            text = stringResource(id = R.string.register_login_button_link),
            style = MaterialTheme.typography.bodyMedium,
            color = LookeoGold,
            modifier = Modifier.clickable {
                onNavigateToLogin()
            }
        )
    }
}