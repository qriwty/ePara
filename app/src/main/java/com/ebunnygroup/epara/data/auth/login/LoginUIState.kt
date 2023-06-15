package com.ebunnygroup.epara.data.auth.login;

data class LoginUIState(
        var email  :String = "",
        var password  :String = "",

        var emailError :Boolean = false,
        var passwordError : Boolean = false
)
