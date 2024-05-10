package com.mugss.mugss.app.authorized.internal.data

import com.google.firebase.auth.FirebaseAuth
import com.mugss.core.network.api.firebase.auth.makeFirebaseRequest
import com.mugss.core.network.api.firebase.user.User
import com.mugss.core.network.api.firebase.user.UserStore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserInfoRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val userStore: UserStore,
) : UserInfoRepository {

    override suspend fun getCurrentUser(): User? = makeFirebaseRequest {
        firebaseAuth.currentUser?.let {
            userStore.firestoreReferences.document(it.uid).get().await()
                .toObject(User::class.java)
        }
    }.getOrNull()

    override fun isAuthorized(): Boolean = firebaseAuth.currentUser != null

    override fun signOut() = firebaseAuth.signOut()
}