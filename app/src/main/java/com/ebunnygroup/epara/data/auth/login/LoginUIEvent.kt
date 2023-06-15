package com.ebunnygroup.epara.data.auth.login

sealed class LoginUIEvent {

    data class EmailChanged(val email:String): LoginUIEvent()
    data class PasswordChanged(val password: String) : LoginUIEvent()

}