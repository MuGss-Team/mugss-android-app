package com.mugss.core.network.internal.firebase.user

import com.google.firebase.Firebase
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.firestore
import com.mugss.core.network.api.firebase.user.UserStore
import javax.inject.Inject

internal class UserStoreImpl @Inject constructor() : UserStore {

    override val firestoreReferences: CollectionReference = Firebase.firestore.collection(
        USERS_COLLECTION
    )

    companion object {
        private const val USERS_COLLECTION = "users"
    }
}