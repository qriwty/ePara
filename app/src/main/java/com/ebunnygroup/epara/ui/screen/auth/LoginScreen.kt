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
import com.ebunnygroup.epara.ui.common.SecretTextFieldComponent
import com.ebunnygroup.epara.ui.common.TextFieldComponent
import com.ebunnygroup.epara.ui.theme.EParaTheme


@Composable
fun LoginScreen(onNextScreenClick: () -> Unit) {
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
                text = "Welcome, our tester",
                style = typography.titleMedium
            )

            Text(
                text = "Login",
                style = typography.titleLarge
            )

            Spacer(modifier = Modifier.height(24.dp))

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
                text = "Login",
                onButtonClicked = onNextScreenClick,
                isEnabled = true
            )

            Spacer(modifier = Modifier.height(24.dp))

            ClickableTextComponent(
                text = "Forgot something?",
                clickable_text = "BAD",
                onTextSelected = {}
            )

            AppWatermarkComponent()

        }

    }
}

@Preview
@Composable
fun PreviewLoginScreen() {
    EParaTheme {
//        LoginScreen()
    }
}