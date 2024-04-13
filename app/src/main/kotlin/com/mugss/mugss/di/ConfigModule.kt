package com.mugss.mugss.di

import com.mugss.core.network.api.ClientId
import com.mugss.core.network.api.ClientSecret
import com.mugss.mugss.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface ConfigModule {

    companion object {

        @Provides
        @ClientId
        fun provideClientId(): String = BuildConfig.CLIENT_ID

        @Provides
        @ClientSecret
        fun provideClientSecret(): String = BuildConfig.CLIENT_SECRET
    }
}