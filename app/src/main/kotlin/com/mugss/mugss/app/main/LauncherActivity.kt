package com.mugss.mugss.app.main

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.mugss.core.compose.theme.MuGssTheme
import com.mugss.mugss.R

class LauncherActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        installSplashScreen()
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        setContent {
            MuGssTheme {
                Box(modifier = Modifier.fillMaxSize().background(MuGssTheme.colors.white), contentAlignment = Alignment.Center) {
                    Image(painter = painterResource(R.drawable.ic_splash), contentDescription = null)
                }
            }
        }
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this@LauncherActivity, MainActivity::class.java))
            finish()
        }, 3000L)
    }
}