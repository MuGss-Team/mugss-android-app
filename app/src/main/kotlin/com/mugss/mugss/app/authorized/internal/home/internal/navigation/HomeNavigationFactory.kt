package com.mugss.mugss.app.authorized.internal.home.internal.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mugss.core.navigation.AuthorizedNavFactory
import com.mugss.mugss.app.authorized.internal.home.api.navigation.Home
import com.mugss.mugss.app.authorized.internal.home.internal.presentation.HomeScreen
import javax.inject.Inject

class HomeNavigationFactory @Inject constructor() : AuthorizedNavFactory {

    override fun create(navGraphBuilder: NavGraphBuilder, navController: NavController) {
        navGraphBuilder.composable<Home> {
            HomeScreen()
        }
    }
}