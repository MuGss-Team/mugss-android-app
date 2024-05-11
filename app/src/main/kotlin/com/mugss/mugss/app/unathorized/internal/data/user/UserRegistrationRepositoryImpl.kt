package com.mugss.mugss.app.unathorized.internal.data.user

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.CollectionReference
import com.mugss.core.network.api.firebase.makeFirebaseRequest
import com.mugss.core.network.api.firebase.user.User
import com.mugss.core.network.api.firebase.user.UserStore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

internal class UserRegistrationRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    @UserStore
    private val userStore: CollectionReference,
) : UserRegistrationRepository {

    override suspend fun register(
        login: String,
        email: String,
        password: String
    ): Result<Unit> = makeFirebaseRequest {
        firebaseAuth.createUserWithEmailAndPassword(
            email,
            password
        ).await().user?.let {
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

    override suspend fun authWithGoogle(
        googleIdToken: String
    ): Result<Unit> = makeFirebaseRequest {
        firebaseAuth.signInWithCredential(
            GoogleAuthProvider.getCredential(
                googleIdToken,
                null
            )
        ).await().user?.let {
            setUserData(
                uuid = it.uid,
                email = it.email,
                login = it.displayName,
                checkExistingRequired = false,
            )
        }
    }

    private suspend fun setUserData(
        uuid: String,
        email: String?,
        login: String?,
        checkExistingRequired: Boolean = true,
    ) {
        val userDocument = userStore.document(uuid)
        if (checkExistingRequired && userDocument.get().await().exists()) {
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
        userStore.whereEqualTo(
            LOGIN_FIELD,
            login
        ).get().await().first().toObject(User::class.java).email
    )

    companion object {
        private const val LOGIN_FIELD = "login"
        private const val EMAIL_FIELD = "email"
    }
}