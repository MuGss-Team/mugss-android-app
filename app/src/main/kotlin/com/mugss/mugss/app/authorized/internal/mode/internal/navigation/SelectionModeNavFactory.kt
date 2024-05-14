package com.mugss.mugss.app.authorized.internal.mode.internal.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mugss.core.navigation.AuthorizedNavFactory
import com.mugss.mugss.app.authorized.internal.mode.api.navigation.SelectionMode
import com.mugss.mugss.app.authorized.internal.mode.internal.presentation.SelectionModeScreen
import javax.inject.Inject

internal class SelectionModeNavFactory @Inject constructor() : AuthorizedNavFactory {

    override fun create(navGraphBuilder: NavGraphBuilder, navController: NavController) {
        navGraphBuilder.composable<SelectionMode> {
            SelectionModeScreen(navController = navController)
        }
    }
}