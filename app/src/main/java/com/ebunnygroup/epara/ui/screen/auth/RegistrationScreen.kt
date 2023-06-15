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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ebunnygroup.epara.data.auth.registration.RegistrationUIEvent
import com.ebunnygroup.epara.data.auth.registration.RegistrationViewModel
import com.ebunnygroup.epara.ui.common.AppWatermarkComponent
import com.ebunnygroup.epara.ui.common.ButtonComponent
import com.ebunnygroup.epara.ui.common.ClickableTextComponent
import com.ebunnygroup.epara.ui.common.SecretTextFieldComponent
import com.ebunnygroup.epara.ui.common.TextFieldComponent


@Composable
fun RegistrationScreen(
    onRegisterClick: () -> Unit,
    onLoginClick: () -> Unit,
    registrationViewModel: RegistrationViewModel = viewModel()
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
                onTextChanged = {
                    registrationViewModel.onEvent(RegistrationUIEvent.FirstNameChanged(it))
                },
                errorStatus = registrationViewModel.registrationUIState.value.firstNameError
            )

            TextFieldComponent(
                labelText = "Surname",
                icon = Icons.Default.People,
                onTextChanged = {
                    registrationViewModel.onEvent(RegistrationUIEvent.LastNameChanged(it))
                },
                errorStatus = registrationViewModel.registrationUIState.value.lastNameError
            )

            TextFieldComponent(
                labelText = "Email",
                icon = Icons.Default.MailOutline,
                onTextChanged = {
                    registrationViewModel.onEvent(RegistrationUIEvent.EmailChanged(it))
                },
                errorStatus = registrationViewModel.registrationUIState.value.emailError
            )

            SecretTextFieldComponent(
                labelText = "Password",
                icon = Icons.Default.Password,
                onTextChanged = {
                    registrationViewModel.onEvent(RegistrationUIEvent.PasswordChanged(it))
                },
                errorStatus = registrationViewModel.registrationUIState.value.passwordError
            )

            Spacer(modifier = Modifier.height(48.dp))

            ButtonComponent(
                text = "Register",
                onButtonClicked = {
                    registrationViewModel.onRegister(onRegisterClick)
                },
                isEnabled = registrationViewModel.allValidationsPassed.value
            )

            Spacer(modifier = Modifier.height(24.dp))

            ClickableTextComponent(
                text = "Do we know you already?",
                clickable_text = "Login",
                onTextClicked = onLoginClick
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