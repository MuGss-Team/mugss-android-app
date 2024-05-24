package com.mugss.mugss.app.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.mugss.core.compose.theme.MuGssTheme
import com.mugss.core.navigation.AppNavFactory
import com.mugss.core.navigation.animations.defaultSlideEnterTransition
import com.mugss.core.navigation.animations.defaultSlideExitTransition
import com.mugss.core.navigation.animations.defaultSlidePopEnterTransition
import com.mugss.core.navigation.animations.defaultSlidePopExitTransition
import com.mugss.mugss.app.main.stateholder.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.collections.immutable.ImmutableSet
import kotlinx.collections.immutable.toImmutableSet

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainActivityViewModel by viewModels<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        mainActivityViewModel.refreshToken()
        if (savedInstanceState == null) {
            mainActivityViewModel.onCreate()
        }
        setContent {
            AppGraph(
                appNavFactories = mainActivityViewModel.appNavFactories.toImmutableSet(),
                startDestination = mainActivityViewModel.getStartDestination()
            )
        }
    }
}

@Composable
private fun AppGraph(
    appNavFactories: ImmutableSet<AppNavFactory>,
    startDestination: Any
) {
    MuGssTheme {
        val navController = rememberNavController()
        NavHost(
            route = Main::class,
            startDestination = startDestination,
            navController = navController,
            exitTransition = { defaultSlideExitTransition() },
            enterTransition = { defaultSlideEnterTransition() },
            popEnterTransition = { defaultSlidePopEnterTransition() },
            popExitTransition = { defaultSlidePopExitTransition() },
        ) {
            appNavFactories.forEach {
                it.create(
                    this,
                    navController
                )
            }
        }
    }
}
