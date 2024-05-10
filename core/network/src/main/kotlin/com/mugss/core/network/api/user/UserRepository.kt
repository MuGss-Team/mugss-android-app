package com.mugss.core.network.api.user

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser

interface UserRepository {

    suspend fun register(
        login: String,
        email: String,
        password: String,
    ): Result<AuthResult?>

    suspend fun auth(
        login: String,
        password: String,
    ): Result<AuthResult?>

    suspend fun getUser(): FirebaseUser?

    suspend fun signOut()
}