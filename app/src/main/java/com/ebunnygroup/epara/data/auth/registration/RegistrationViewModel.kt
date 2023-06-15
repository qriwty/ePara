package com.ebunnygroup.epara.data.auth.registration

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.ebunnygroup.epara.data.auth.rules.Validator


class RegistrationViewModel : ViewModel() {
    private val TAG = RegistrationViewModel::class.simpleName

    var registrationUIState = mutableStateOf(RegistrationUIState())

    var allValidationsPassed = mutableStateOf(false)

    var registrationInProgress = mutableStateOf(false)

    fun onEvent(event: RegistrationUIEvent) {
        when (event) {
            is RegistrationUIEvent.FirstNameChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    firstName = event.firstName
                )
            }

            is RegistrationUIEvent.LastNameChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    lastName = event.lastName
                )
            }

            is RegistrationUIEvent.EmailChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    email = event.email
                )

            }

            is RegistrationUIEvent.PasswordChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    password = event.password
                )
            }

        }
        validateDataWithRules()
    }

    fun onRegister(onRegisterClicked: () -> Unit) {
        onRegisterClicked.invoke()
    }

    private fun validateDataWithRules() {
        val firstNameResult = Validator.validateFirstName(
            firstName = registrationUIState.value.firstName
        )

        val lastNameResult = Validator.validateLastName(
            lastName = registrationUIState.value.lastName
        )

        val emailResult = Validator.validateEmail(
            email = registrationUIState.value.email
        )

        val passwordResult = Validator.validatePassword(
            password = registrationUIState.value.password
        )

        registrationUIState.value = registrationUIState.value.copy(
            firstNameError = !firstNameResult.status,
            lastNameError = !lastNameResult.status,
            emailError = !emailResult.status,
            passwordError = !passwordResult.status
        )

        allValidationsPassed.value = firstNameResult.status && lastNameResult.status &&
                emailResult.status && passwordResult.status

    }


}