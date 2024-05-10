package com.mugss.mugss.app.unathorized.internal.di

import com.mugss.core.navigation.AppNavFactory
import com.mugss.core.navigation.UnauthorizedNavFactory
import com.mugss.mugss.app.unathorized.internal.authorization.internal.navigation.AuthorizationNavFactory
import com.mugss.mugss.app.unathorized.internal.data.UserRegistrationRepository
import com.mugss.mugss.app.unathorized.internal.data.UserRegistrationRepositoryImpl
import com.mugss.mugss.app.unathorized.internal.navigation.UnauthorizedGraphNavFactory
import com.mugss.mugss.app.unathorized.internal.registration.internal.navigation.RegistrationNavFactory
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
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
}