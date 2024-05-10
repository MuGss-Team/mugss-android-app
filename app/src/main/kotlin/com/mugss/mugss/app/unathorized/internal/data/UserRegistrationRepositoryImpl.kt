package com.mugss.mugss.app.unathorized.internal.data

import com.google.firebase.auth.FirebaseAuth
import com.mugss.core.network.api.firebase.auth.makeFirebaseRequest
import com.mugss.core.network.api.firebase.user.User
import com.mugss.core.network.api.firebase.user.UserStore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

internal class UserRegistrationRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val userStore: UserStore,
) : UserRegistrationRepository {

    override suspend fun register(
        login: String,
        email: String,
        password: String
    ): Result<Unit> = makeFirebaseRequest {
        val user = firebaseAuth.createUserWithEmailAndPassword(
            email,
            password
        ).await().user
        user?.let {
            setUserData(
                uuid = it.uid,
                email = email,
                login = login
            )
        }
    }

    override suspend fun auth(
        login: String,
        password: String
    ): Result<Unit> = makeFirebaseRequest {
        val email = requireUserEmail(login)
        firebaseAuth.signInWithEmailAndPassword(
            email,
            password
        ).await()
    }

    private suspend fun setUserData(
        uuid: String,
        email: String,
        login: String
    ) {
        val userDocument = userStore.firestoreReferences.document(uuid)
        if (userDocument.get().await().exists()) {
            throw UserAlreadyExistException()
        }
        userDocument.set(
            hashMapOf(
                EMAIL_FIELD to email,
                LOGIN_FIELD to login
            )
        )
    }

    private suspend fun requireUserEmail(
        login: String
    ) = requireNotNull(
        userStore.firestoreReferences.whereEqualTo(
            LOGIN_FIELD,
            login
        ).get().await().first().toObject(User::class.java).email
    )

    companion object {
        private const val LOGIN_FIELD = "login"
        private const val EMAIL_FIELD = "email"
    }
}