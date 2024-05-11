package com.mugss.mugss.app.authorized.internal.data

import com.mugss.core.network.api.firebase.user.User

interface UserInfoRepository {

    suspend fun getCurrentUser(): User?

    fun isAuthorized(): Boolean

    suspend fun signOut()
}