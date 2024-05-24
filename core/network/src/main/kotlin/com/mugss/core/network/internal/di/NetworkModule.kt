package com.mugss.core.network.internal.di

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.mugss.core.data.token.TokenStore
import com.mugss.core.network.api.ClientId
import com.mugss.core.network.api.ClientSecret
import com.mugss.core.network.api.firebase.modes.ModesStore
import com.mugss.core.network.api.firebase.playlist.PlaylistStore
import com.mugss.core.network.api.firebase.top.TopsStore
import com.mugss.core.network.api.firebase.user.UserStore
import com.mugss.core.network.api.spotify.SpotifyApi
import com.mugss.core.network.internal.MuGssApi
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.android.Android
import io.ktor.client.engine.android.AndroidEngineConfig
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi


@Module
@InstallIn(SingletonComponent::class)
internal interface NetworkModule {

    @Binds
    fun bindPlaylistApi(muGssApi: MuGssApi): SpotifyApi

    companion object {

        @Singleton
        @Provides
        fun firebaseAuth() = Firebase.auth

        @Singleton
        @Provides
        @SpotifyClient
        fun provideHttpClient(
            tokenStore: TokenStore,
        ): HttpClient = HttpClient(Android) {
            installDefault()
            defaultRequest {
                url(BASE_URL)
                header(HttpHeaders.Authorization, "Bearer ${tokenStore.token}")
            }
        }

        @OptIn(ExperimentalEncodingApi::class)
        @Singleton
        @Provides
        @TokenClient
        fun provideHttpTokenClient(
            @ClientId
            clientId: String,
            @ClientSecret
            clientSecret: String,
        ): HttpClient = HttpClient(Android) {
            installDefault()
            defaultRequest {
                url(TOKEN_URL)
                header(
                    HttpHeaders.Authorization,
                    "Basic " + Base64.Default.encode(
                        ("$clientId:$clientSecret").toByteArray()
                    )
                )
            }
        }

        @Singleton
        @Provides
        @ModesStore
        fun provideModesStore() = Firebase.firestore.collection(
            MODES_STORE
        )

        @Singleton
        @Provides
        @UserStore
        fun provideUserStore() = Firebase.firestore.collection(
            USERS_STORE
        )

        @Singleton
        @Provides
        @PlaylistStore
        fun providePlaylistStore() = Firebase.firestore.collection(
            PLAYLIST_STORE
        )

        @Singleton
        @Provides
        @TopsStore
        fun provideTopsStore() = Firebase.firestore.collection(
            TOPS_STORE
        )

        private fun HttpClientConfig<AndroidEngineConfig>.installDefault() {
            install(Logging)
            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                    }
                )
            }
            engine {
                socketTimeout = TIME_OUT
                connectTimeout = TIME_OUT

            }
        }

        private const val TIME_OUT = 5000
        private const val BASE_URL = "https://api.spotify.com/v1/"
        private const val TOKEN_URL = "https://accounts.spotify.com/api/token/"
        private const val MODES_STORE = "modes"
        private const val USERS_STORE = "users"
        private const val PLAYLIST_STORE = "playlists"
        private const val TOPS_STORE = "tops"
    }
}