package com.mugss.mugss.app.unathorized.internal.authorization.internal.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mugss.core.navigation.UnauthorizedNavFactory
import com.mugss.mugss.app.unathorized.internal.authorization.api.navigation.Authorization
import com.mugss.mugss.app.unathorized.internal.authorization.internal.presentation.AuthorizationScreen
import javax.inject.Inject

internal class AuthorizationNavFactory @Inject constructor() : UnauthorizedNavFactory {

    override fun create(navGraphBuilder: NavGraphBuilder, navController: NavController) {
        navGraphBuilder.composable<Authorization> {
            AuthorizationScreen(
                navController = navController
            )
        }
    }
}