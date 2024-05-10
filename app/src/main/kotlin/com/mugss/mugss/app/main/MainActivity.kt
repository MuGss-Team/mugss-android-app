package com.mugss.mugss.app.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.mugss.core.compose.theme.MuGssTheme
import com.mugss.mugss.app.main.stateholder.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

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
            MuGssTheme {
                val navController = rememberNavController()
                NavHost(
                    route = Main::class,
                    startDestination = mainActivityViewModel.getStartDestination(),
                    navController = navController,
                ) {
                    mainActivityViewModel.appNavFactories.forEach {
                        it.create(
                            this,
                            navController
                        )
                    }
                }
            }
        }
    }
}
