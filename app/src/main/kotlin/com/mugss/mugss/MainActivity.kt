package com.mugss.mugss

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.mugss.core.compose.theme.MuGssTheme
import com.mugss.core.network.api.token.TokenRefresher
import com.mugss.mugss.app.registration.RegistrationScreen
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var tokenRefresher: TokenRefresher

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            MuGssTheme {
                RegistrationScreen()
            }
        }
    }
}