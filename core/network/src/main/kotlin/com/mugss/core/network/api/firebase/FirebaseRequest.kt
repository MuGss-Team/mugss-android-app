package com.mugss.core.network.api.firebase

import com.google.android.gms.tasks.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

suspend fun <T> makeFirebaseRequest(
    request: suspend () -> T,
): Result<T> = runCatching {
    withContext(Dispatchers.IO) {
        request()
    }
}

suspend fun <T> makeNonSafeFirebaseRequest(
    request: () -> Task<T>,
): T = withContext(Dispatchers.IO) {
    request().await()
}

