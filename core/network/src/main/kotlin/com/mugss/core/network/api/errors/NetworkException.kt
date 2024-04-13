package com.mugss.core.network.api.errors

import io.ktor.http.HttpStatusCode

class NetworkException(
    val status: HttpStatusCode
) : Exception()