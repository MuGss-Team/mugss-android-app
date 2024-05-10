package com.mugss.mugss.app.registration.contract

import androidx.compose.runtime.Immutable

@Immutable
data class RegistrationState(
    val login: String = "",
    val email: String = "",
    val password: String = "",
    val confirmationPassword: String = "",
)