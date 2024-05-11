package com.mugss.mugss.app.unathorized.internal.data.user

internal interface UserRegistrationRepository {

    suspend fun register(
        login: String,
        email: String,
        password: String,
    ): Result<Unit>

    suspend fun authWithGoogle(
        googleIdToken: String,
    ): Result<Unit>

    suspend fun auth(
        login: String,
        password: String,
    ): Result<Unit>
}