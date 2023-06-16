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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ebunnygroup.epara.data.auth.login.LoginUIEvent
import com.ebunnygroup.epara.data.auth.login.LoginViewModel
import com.ebunnygroup.epara.ui.common.AppWatermarkComponent
import com.ebunnygroup.epara.ui.common.ButtonComponent
import com.ebunnygroup.epara.ui.common.ClickableTextComponent
import com.ebunnygroup.epara.ui.common.SecretTextFieldComponent
import com.ebunnygroup.epara.ui.common.TextFieldComponent
import com.ebunnygroup.epara.ui.theme.EParaTheme


@Composable
fun LoginScreen(
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit,
    loginViewModel: LoginViewModel = viewModel()
) {
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
                onTextChanged = {
                    loginViewModel.onEvent(LoginUIEvent.EmailChanged(it))
                },
                errorStatus = loginViewModel.loginUIState.value.emailError
            )

            SecretTextFieldComponent(
                labelText = "Password",
                icon = Icons.Default.Password,
                onTextChanged = {
                    loginViewModel.onEvent(LoginUIEvent.PasswordChanged(it))
                },
                errorStatus = loginViewModel.loginUIState.value.passwordError
            )

            Spacer(modifier = Modifier.height(48.dp))

            ButtonComponent(
                text = "Login",
                onButtonClicked = {
                    loginViewModel.onLogin(onLoginClicked = onLoginClick)
                },
                isEnabled = loginViewModel.allValidationsPassed.value
            )

            Spacer(modifier = Modifier.height(24.dp))

            ClickableTextComponent(
                text = "Need a registration?",
                clickable_text = "Welcome",
                onTextClicked = onRegisterClick
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