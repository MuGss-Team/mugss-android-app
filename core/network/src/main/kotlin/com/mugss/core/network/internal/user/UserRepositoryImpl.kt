package com.mugss.core.network.internal.user

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.mugss.core.network.api.user.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject


internal class UserRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
) : UserRepository {

    override suspend fun register(
        login: String,
        email: String,
        password: String
    ): Result<AuthResult?> = runCatching {
        withContext(Dispatchers.IO) {
            auth.createUserWithEmailAndPassword(
                email,
                password,
            ).await()
        }
    }

    override suspend fun auth(
        login: String,
        password: String
    ): Result<AuthResult?> = runCatching {
        withContext(Dispatchers.IO) {
            auth.signInWithEmailAndPassword(
                login, password
            ).await()
        }
    }


    override suspend fun signOut() = withContext(Dispatchers.IO) {
        auth.signOut()
    }

    override suspend fun getUser(): FirebaseUser? = auth.currentUser
}