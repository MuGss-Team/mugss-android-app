package com.mugss.mugss.app.authorized.internal.data

import com.google.firebase.auth.FirebaseAuth
import com.mugss.core.network.api.firebase.auth.makeFirebaseRequest
import com.mugss.core.network.api.firebase.user.User
import com.mugss.core.network.api.firebase.user.UserStore
import com.mugss.mugss.app.unathorized.internal.data.credential.CredentialManagerWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class UserInfoRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val userStore: UserStore,
    private val credentialManagerWrapper: CredentialManagerWrapper,
) : UserInfoRepository {

    override suspend fun getCurrentUser(): User? = makeFirebaseRequest {
        firebaseAuth.currentUser?.let {
            userStore.firestoreReferences.document(it.uid).get().await()
                .toObject(User::class.java)
        }
    }.getOrNull()

    override fun isAuthorized(): Boolean = firebaseAuth.currentUser != null

    override suspend fun signOut() {
        withContext(Dispatchers.Main.immediate) {
            firebaseAuth.signOut()
        }
        credentialManagerWrapper.signOut()
    }
}