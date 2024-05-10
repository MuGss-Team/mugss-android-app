package com.mugss.mugss.app.unathorized.internal.data

internal interface UserRegistrationRepository {

    suspend fun register(
        login: String,
        email: String,
        password: String,
    ): Result<Unit>

    suspend fun auth(
        login: String,
        password: String,
    ): Result<Unit>
}