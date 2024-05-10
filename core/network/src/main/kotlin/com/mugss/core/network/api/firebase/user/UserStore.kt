package com.mugss.core.network.api.firebase.user

import com.google.firebase.firestore.CollectionReference

interface UserStore {
    val firestoreReferences: CollectionReference
}