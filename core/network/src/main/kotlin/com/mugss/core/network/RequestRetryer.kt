package com.mugss.core.network

import com.mugss.core.network.api.errors.NetworkException
import com.mugss.core.network.api.token.TokenRefresher
import io.ktor.http.HttpStatusCode
import javax.inject.Inject


class RequestRetryer @Inject constructor(
    private val tokenRefresher: TokenRefresher,
) {

    internal suspend fun <T> sendRetryingRequest(
        sendRetryingRequest: suspend () -> Result<T>,
    ): Result<T> {
        var countOfRetries = 1
        var result: Result<T> = sendRetryingRequest()
        while (countOfRetries < MAX_COUNT_OF_RETRIES) {
            if (result.isSuccess) {
                return result
            }
            mapPreAction(result.exceptionOrNull())

            result = sendRetryingRequest()
            countOfRetries++
        }
        return result
    }

    private suspend fun mapPreAction(
        throwable: Throwable?
    ) {
        if (throwable is NetworkException) {
            when (throwable.status) {
                HttpStatusCode.Unauthorized -> tokenRefresher.refreshToken()
            }
        }
    }


    companion object {
        private const val MAX_COUNT_OF_RETRIES = 3
    }
}