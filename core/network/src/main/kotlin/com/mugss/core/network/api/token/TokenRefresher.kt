package com.mugss.core.network.api.token

import com.mugss.core.data.token.TokenStore
import com.mugss.core.network.internal.di.TokenClient
import com.mugss.core.network.internal.getResponseResult
import io.ktor.client.HttpClient
import io.ktor.client.request.forms.submitForm
import io.ktor.http.parameters
import javax.inject.Inject

class TokenRefresher @Inject constructor(
    @TokenClient
    private val tokenClient: HttpClient,
    private val tokenStore: TokenStore,
) {

    suspend fun refreshToken() {
        getResponseResult<TokenDataModel> {
            tokenClient.submitForm(
                formParameters = parameters {
                    append(
                        name = "grant_type",
                        value = "client_credentials"
                    )
                }
            )
        }.onSuccess {
            tokenStore.token = it.accessToken
        }
    }
}