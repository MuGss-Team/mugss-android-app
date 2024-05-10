package com.mugss.mugss.app.unathorized.internal.authorization.internal.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import com.mugss.mugss.app.authorized.api.navigation.AuthorizedGraph
import com.mugss.mugss.app.main.Main
import com.mugss.mugss.app.unathorized.internal.authorization.internal.presentation.stateholder.AuthorizationViewModel

@Composable
internal fun NavigationEventsHandler(
    authorizationViewModel: AuthorizationViewModel,
    navController: NavController,
) {
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(key1 = Unit) {
        lifecycleOwner.repeatOnLifecycle(
            Lifecycle.State.RESUMED
        ) {
            authorizationViewModel.navigationFlow.collect {
                when (it) {
                    AuthorizedGraph -> navController.navigate(it) {
                        popUpTo<Main>()
                    }
                }
            }
        }
    }
}