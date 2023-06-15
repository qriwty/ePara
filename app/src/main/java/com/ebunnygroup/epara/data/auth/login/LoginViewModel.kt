package com.ebunnygroup.epara.data.auth.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.ebunnygroup.epara.data.auth.rules.Validator
import com.google.firebase.auth.FirebaseAuth


class LoginViewModel : ViewModel() {
    private val TAG = LoginViewModel::class.simpleName

    var loginUIState = mutableStateOf(LoginUIState())

    var allValidationsPassed = mutableStateOf(false)

    var loginInProgress = mutableStateOf(false)

    fun onEvent(event: LoginUIEvent) {
        when (event) {
            is LoginUIEvent.EmailChanged -> {
                loginUIState.value = loginUIState.value.copy(
                    email = event.email
                )
            }

            is LoginUIEvent.PasswordChanged -> {
                loginUIState.value = loginUIState.value.copy(
                    password = event.password
                )
            }

        }
        validateLoginUIDataWithRules()
    }

    fun onLogin(onLoginClicked: () -> Unit) {
        loginInProgress.value = true

        val email = loginUIState.value.email
        val password = loginUIState.value.password

        val auth = FirebaseAuth.getInstance()

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    loginInProgress.value = false
                    onLoginClicked.invoke()
                }
            }
            .addOnFailureListener {
                loginInProgress.value = false
            }

    }

    private fun validateLoginUIDataWithRules() {
        val emailResult = Validator.validateEmail(
            email = loginUIState.value.email
        )

        val passwordResult = Validator.validatePassword(
            password = loginUIState.value.password
        )

        loginUIState.value = loginUIState.value.copy(
            emailError = !emailResult.status,
            passwordError = !passwordResult.status
        )

        allValidationsPassed.value = emailResult.status && passwordResult.status

    }

}