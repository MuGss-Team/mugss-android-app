package com.mugss.mugss.app.main.stateholder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mugss.core.data.token.UserPrefs
import com.mugss.core.navigation.AppNavFactory
import com.mugss.core.network.api.token.TokenRefresher
import com.mugss.mugss.app.authorized.api.navigation.AuthorizedGraph
import com.mugss.mugss.app.authorized.internal.data.UserInfoRepository
import com.mugss.mugss.app.unathorized.api.navigation.UnauthorizedGraph
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainActivityViewModel @Inject constructor(
    val appNavFactories: @JvmSuppressWildcards Set<AppNavFactory>,
    private val tokenRefresher: TokenRefresher,
    private val userInfoRepository: UserInfoRepository,
    private val userPrefs: UserPrefs,
) : ViewModel() {

    fun getStartDestination(): Any = if (userInfoRepository.isAuthorized()) {
        AuthorizedGraph
    } else {
        UnauthorizedGraph
    }

    fun refreshToken() = viewModelScope.launch {
        tokenRefresher.refreshToken()
    }

    fun onCreate() = viewModelScope.launch {
        if (!userPrefs.rememberMe) {
            userInfoRepository.signOut()
        }
    }
}