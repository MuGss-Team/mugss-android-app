package com.mugss.mugss.app.unathorized.internal.registration.internal.contract

import androidx.compose.runtime.Immutable
import kotlinx.serialization.Serializable

@Immutable
@Serializable
internal data class RegistrationState(
    val login: String = "",
    val email: String = "",
    val password: String = "",
    val confirmationPassword: String = "",
)