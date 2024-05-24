package com.mugss.mugss.app.authorized.internal.di

import com.mugss.core.navigation.AppNavFactory
import com.mugss.core.navigation.AuthorizedNavFactory
import com.mugss.mugss.app.authorized.internal.data.UserInfoRepository
import com.mugss.mugss.app.authorized.internal.data.UserInfoRepositoryImpl
import com.mugss.mugss.app.authorized.internal.game.internal.data.PlaylistRepository
import com.mugss.mugss.app.authorized.internal.game.internal.data.PlaylistRepositoryImpl
import com.mugss.mugss.app.authorized.internal.game.internal.navigation.GameScreenNavFactory
import com.mugss.mugss.app.authorized.internal.home.internal.navigation.HomeNavigationFactory
import com.mugss.mugss.app.authorized.internal.mode.internal.data.ModesRepository
import com.mugss.mugss.app.authorized.internal.mode.internal.data.ModesRepositoryImpl
import com.mugss.mugss.app.authorized.internal.mode.internal.navigation.SelectionModeNavFactory
import com.mugss.mugss.app.authorized.internal.navigation.AuthorizedGraphNavFactory
import com.mugss.mugss.app.authorized.internal.tops.internal.data.TopsRepository
import com.mugss.mugss.app.authorized.internal.tops.internal.data.TopsRepositoryImpl
import com.mugss.mugss.app.authorized.internal.tops.internal.navigation.TopsNavFactory
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

    @Binds
    @IntoSet
    fun bindSelectionModeNavFactory(selectionModeNavFactory: SelectionModeNavFactory): AuthorizedNavFactory

    @Binds
    @IntoSet
    fun bindTopsNavFactory(topsNavFactory: TopsNavFactory): AuthorizedNavFactory

    @Binds
    fun bindModesRepository(modesRepositoryImpl: ModesRepositoryImpl): ModesRepository

    @Binds
    fun bindTopsRepository(topsRepositoryImpl: TopsRepositoryImpl): TopsRepository

    @Binds
    fun bindPlaylistRepository(playlistRepositoryImpl: PlaylistRepositoryImpl): PlaylistRepository

    @Binds
    @IntoSet
    fun bindGameScreenNavFactory(gameScreenNavFactory: GameScreenNavFactory): AuthorizedNavFactory
}