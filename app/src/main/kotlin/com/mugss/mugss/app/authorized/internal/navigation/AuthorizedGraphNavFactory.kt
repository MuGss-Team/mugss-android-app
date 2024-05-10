package com.mugss.mugss.app.authorized.internal.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.mugss.core.navigation.AppNavFactory
import com.mugss.core.navigation.AuthorizedNavFactory
import com.mugss.mugss.app.authorized.api.navigation.AuthorizedGraph
import com.mugss.mugss.app.authorized.internal.home.api.navigation.Home
import javax.inject.Inject

class AuthorizedGraphNavFactory @Inject constructor(
    private val authorizedNavFactories: @JvmSuppressWildcards Set<AuthorizedNavFactory>,
) : AppNavFactory {

    override fun create(navGraphBuilder: NavGraphBuilder, navController: NavController) {
        navGraphBuilder.navigation<AuthorizedGraph>(Home) {
            authorizedNavFactories.forEach {
                it.create(this, navController)
            }
        }
    }
}