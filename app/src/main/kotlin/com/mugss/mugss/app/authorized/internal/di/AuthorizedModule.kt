package com.mugss.mugss.app.authorized.internal.di

import com.mugss.core.navigation.AppNavFactory
import com.mugss.core.navigation.AuthorizedNavFactory
import com.mugss.mugss.app.authorized.internal.data.UserInfoRepository
import com.mugss.mugss.app.authorized.internal.data.UserInfoRepositoryImpl
import com.mugss.mugss.app.authorized.internal.home.internal.navigation.HomeNavigationFactory
import com.mugss.mugss.app.authorized.internal.navigation.AuthorizedGraphNavFactory
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet


@Module
@InstallIn(SingletonComponent::class)
internal interface AuthorizedModule {

    @Binds
    fun bindUserInfoRepository(userInfoRepository: UserInfoRepositoryImpl): UserInfoRepository

    @Binds
    @IntoSet
    fun bindAuthorizedGraphNavFactory(authorizedGraphNavFactory: AuthorizedGraphNavFactory): AppNavFactory

    @Binds
    @IntoSet
    fun bindHomeNavFactory(homeNavigationFactory: HomeNavigationFactory): AuthorizedNavFactory
}