package com.mugss.core.network.api.token

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TokenDataModel(
    @SerialName("access_token")
    val accessToken: String = ""
)