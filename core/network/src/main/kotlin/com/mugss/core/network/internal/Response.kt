package com.mugss.core.network.internal

import com.mugss.core.network.api.errors.NetworkException
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.isSuccess

internal suspend inline fun <reified T> getResponseResult(
    request: () -> HttpResponse
): Result<T> = runCatching {
    val response = request()

    if (response.status.isSuccess()) {
        return Result.success(response.body<T>())
    }

    throw NetworkException(response.status)
}