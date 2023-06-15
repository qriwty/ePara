package com.ebunnygroup.epara.ui.screen.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.People
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ebunnygroup.epara.ui.common.AppWatermarkComponent
import com.ebunnygroup.epara.ui.common.ButtonComponent
import com.ebunnygroup.epara.ui.common.ClickableTextComponent
import com.ebunnygroup.epara.ui.common.ScreenContent
import com.ebunnygroup.epara.ui.common.SecretTextFieldComponent
import com.ebunnygroup.epara.ui.common.TextFieldComponent


@Composable
fun RegistrationScreen(onRegisterClick: () -> Unit, onLoginClick: () -> Unit) {
    val typography = MaterialTheme.typography
    val colors = MaterialTheme.colorScheme

    Surface (
        modifier = Modifier.fillMaxSize()
    ) {

        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(28.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Welcome, our newbie",
                style = typography.titleMedium
            )

            Text(
                text = "Register",
                style = typography.titleLarge
            )

            Spacer(modifier = Modifier.height(24.dp))

            TextFieldComponent(
                labelText = "Name",
                icon = Icons.Default.People,
                onTextChanged = {},
                errorStatus = false
            )

            TextFieldComponent(
                labelText = "Surname",
                icon = Icons.Default.People,
                onTextChanged = {},
                errorStatus = false
            )

            TextFieldComponent(
                labelText = "Email",
                icon = Icons.Default.MailOutline,
                onTextChanged = {},
                errorStatus = false
            )

            SecretTextFieldComponent(
                labelText = "Password",
                icon = Icons.Default.Password,
                onTextSelected = {},
                errorStatus = false
            )

            Spacer(modifier = Modifier.height(48.dp))

            ButtonComponent(
                text = "Register",
                onButtonClicked = onRegisterClick,
                isEnabled = true
            )

            Spacer(modifier = Modifier.height(24.dp))

            ClickableTextComponent(
                text = "Do we know you already?",
                clickable_text = "Login",
                onTextSelected = onLoginClick
            )

            AppWatermarkComponent()

        }

    }
}

@Preview
@Composable
fun RegistrationScreenPreview() {
//    RegistrationScreen()
}