package com.ebunnygroup.epara.data.auth.registration

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.ebunnygroup.epara.data.auth.rules.Validator
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest


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
        registrationInProgress.value = true

        val email = registrationUIState.value.email
        val password = registrationUIState.value.password

        val firstName = registrationUIState.value.firstName
        val lastName = registrationUIState.value.lastName

        val auth = FirebaseAuth.getInstance()

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    val user = auth.currentUser
                    val userProfileChangeRequest = UserProfileChangeRequest.Builder()
                        .setDisplayName("$firstName $lastName")
                        .build()

                    user?.updateProfile(userProfileChangeRequest)

                    onRegisterClicked.invoke()

                }
            }

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