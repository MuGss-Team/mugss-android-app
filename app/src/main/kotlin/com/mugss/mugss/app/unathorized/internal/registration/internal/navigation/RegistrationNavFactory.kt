package com.mugss.mugss.app.unathorized.internal.registration.internal.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mugss.core.navigation.UnauthorizedNavFactory
import com.mugss.mugss.app.unathorized.internal.registration.api.navigation.Registration
import com.mugss.mugss.app.unathorized.internal.registration.internal.presentation.RegistrationScreen
import javax.inject.Inject

internal class RegistrationNavFactory @Inject constructor() : UnauthorizedNavFactory {

    override fun create(navGraphBuilder: NavGraphBuilder, navController: NavController) {
        navGraphBuilder.composable<Registration> {
            RegistrationScreen(navController)
        }
    }
}