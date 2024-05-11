package com.mugss.mugss.app.unathorized.internal.di

import android.content.Context
import androidx.credentials.GetCredentialRequest
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.mugss.core.navigation.AppNavFactory
import com.mugss.core.navigation.UnauthorizedNavFactory
import com.mugss.mugss.R
import com.mugss.mugss.app.unathorized.internal.authorization.internal.navigation.AuthorizationNavFactory
import com.mugss.mugss.app.unathorized.internal.data.user.UserRegistrationRepository
import com.mugss.mugss.app.unathorized.internal.data.user.UserRegistrationRepositoryImpl
import com.mugss.mugss.app.unathorized.internal.navigation.UnauthorizedGraphNavFactory
import com.mugss.mugss.app.unathorized.internal.registration.internal.navigation.RegistrationNavFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
internal interface UnauthorizedModule {

    @Binds
    fun bindUserRegistrationRepository(
        userRegistrationRepositoryImpl: UserRegistrationRepositoryImpl
    ): UserRegistrationRepository

    @Binds
    @IntoSet
    fun bindUnauthorized(
        unauthorizedGraphNavFactory: UnauthorizedGraphNavFactory
    ): AppNavFactory

    @Binds
    @IntoSet
    fun bindRegistrationFactory(
        registrationNavFactory: RegistrationNavFactory
    ): UnauthorizedNavFactory

    @Binds
    @IntoSet
    fun bindAuthorizationFactory(
        authorizationNavFactory: AuthorizationNavFactory
    ): UnauthorizedNavFactory

    companion object {

        @Provides
        fun provideGetCredentialRequest(
            @ApplicationContext context: Context,
        ): GetCredentialRequest = GetCredentialRequest.Builder().addCredentialOption(
            GetGoogleIdOption.Builder()
                .setFilterByAuthorizedAccounts(false)
                .setServerClientId(context.getString(R.string.default_web_client_id))
                .build()
        ).build()
    }
}