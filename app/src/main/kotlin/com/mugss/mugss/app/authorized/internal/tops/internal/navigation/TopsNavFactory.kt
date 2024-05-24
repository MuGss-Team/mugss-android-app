package com.mugss.mugss.app.authorized.internal.tops.internal.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mugss.core.navigation.AuthorizedNavFactory
import com.mugss.mugss.app.authorized.internal.tops.api.navigation.Tops
import com.mugss.mugss.app.authorized.internal.tops.internal.presentation.TopsScreen
import javax.inject.Inject

internal class TopsNavFactory @Inject constructor(): AuthorizedNavFactory {
    override fun create(navGraphBuilder: NavGraphBuilder, navController: NavController) {
        navGraphBuilder.composable<Tops> {
            TopsScreen(navController = navController)
        }
    }
}