package com.mugss.mugss.app.unathorized.internal.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.mugss.core.navigation.AppNavFactory
import com.mugss.core.navigation.UnauthorizedNavFactory
import com.mugss.mugss.app.unathorized.api.navigation.UnauthorizedGraph
import com.mugss.mugss.app.unathorized.internal.authorization.api.navigation.Authorization
import javax.inject.Inject

internal class UnauthorizedGraphNavFactory @Inject constructor(
    private val unauthorizedNavFactories: @JvmSuppressWildcards Set<UnauthorizedNavFactory>,
) : AppNavFactory {

    override fun create(navGraphBuilder: NavGraphBuilder, navController: NavController) {
        navGraphBuilder.navigation<UnauthorizedGraph>(Authorization) {
            unauthorizedNavFactories.forEach {
                it.create(this, navController)
            }
        }
    }
}