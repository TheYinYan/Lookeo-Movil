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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
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
import com.alanturing.lookeo_movil.core.ui.theme.InterRegular
import com.alanturing.lookeo_movil.core.ui.theme.LookeoDarkGrey
import com.alanturing.lookeo_movil.core.ui.theme.PlayfairDisplayBold
import com.alanturing.lookeo_movil.core.ui.theme.PlayfairDisplayRegular

@Composable
fun RegisterScreen(
    onNavigateToLogin: () -> Unit,
    onNavigateToHome: () -> Unit
) {
    val viewModel: RegisterViewModel = viewModel()
    var acceptedTerms by remember {
        mutableStateOf(false)
    }

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
                .padding(top = 10.dp)
                .verticalScroll(rememberScrollState()),

            verticalArrangement = Arrangement.Center
        ) {
            val data by viewModel.data.collectAsStateWithLifecycle()

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
            val customQuantityKeyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text
            )

            Text(
                text = stringResource(id = R.string.register_user_label),
                fontFamily = InterRegular,
                fontSize = 15.sp,
                color = LookeoGray,
            )
            TextField(
                modifier = textFieldsModifier,
                value = data.userName,
                onValueChange = { viewModel.createName(it) },
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
                fontFamily = InterRegular,
                fontSize = 15.sp,
                color = LookeoGray,
            )
            TextField(
                modifier = textFieldsModifier,
                value = data.email,
                onValueChange = { viewModel.createEmail(it) },
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
                fontFamily = InterRegular,
                fontSize = 15.sp,
                color = LookeoGray,
            )
            TextField(
                modifier = textFieldsModifier,
                value = data.password,
                onValueChange = { viewModel.createPassword(it) },
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
                fontFamily = InterRegular,
                fontSize = 15.sp,
                color = LookeoGray,
            )
            TextField(
                modifier = textFieldsModifier,
                value = data.password,
                onValueChange = { viewModel.createPassword(it) },
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
                fontFamily = InterRegular,
                fontSize = 12.sp,
                color = LookeoGray,
            )
            Spacer(Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = acceptedTerms,
                    onCheckedChange = { newValue ->
                        acceptedTerms = newValue
                    }
                )
                Text(
                    text = stringResource(id = R.string.register_accept) + " ",
                    fontFamily = InterRegular,
                    fontSize = 11.sp,
                    color = LookeoGray
                )
                Text(
                    text = stringResource(id = R.string.register_terms) + " ",
                    fontFamily = InterRegular,
                    fontSize = 11.sp,
                    color = LookeoGold
                )
                Text(
                    text = stringResource(id = R.string.register_and) + " ",
                    fontFamily = InterRegular,
                    fontSize = 11.sp,
                    color = LookeoGray
                )
                Text(
                    text = stringResource(id = R.string.register_privacy),
                    fontFamily = InterRegular,
                    fontSize = 11.sp,
                    color = LookeoGold
                )
            }

            Button(
                enabled = acceptedTerms,
                onClick = onNavigateToHome,
                colors = ButtonDefaults.buttonColors(
                    containerColor = LookeoGold,
                    contentColor = LookeoBlack
                ),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.register_button),
                    fontFamily = InterRegular,
                    fontSize = 15.sp,
                    color = LookeoBlack
                )
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
            fontFamily = InterRegular,
            fontSize = 15.sp,
            color = LookeoGray
        )
        Text(
            text = stringResource(id = R.string.register_login_button_link),
            fontFamily = InterBold,
            fontSize = 15.sp,
            color = LookeoGold,
            modifier = Modifier.clickable {
                onNavigateToLogin()
            }
        )
    }
}