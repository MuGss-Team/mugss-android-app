package com.mugss.mugss.app.unathorized.internal.authorization.internal.presentation.contract

import androidx.compose.runtime.Immutable
import kotlinx.serialization.Serializable

@Immutable
@Serializable
internal data class AuthorizationState(
    val login: String = "",
    val password: String = "",
    val rememberMe: Boolean = false,
)