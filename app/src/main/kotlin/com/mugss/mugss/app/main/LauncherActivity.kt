package com.mugss.mugss.app.main

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.mugss.core.compose.theme.MuGssTheme
import com.mugss.core.compose.theme.typo.shadow.ShadowOffset
import com.mugss.core.compose.theme.typo.shadow.withShadow
import com.mugss.mugss.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LauncherActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        installSplashScreen()
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        setContent {
            MuGssTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MuGssTheme.colors.white), contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = getString(R.string.app_name),
                        style = MuGssTheme.typography.titleXXl.withShadow(ShadowOffset.BIG),
                        color = MuGssTheme.colors.primary
                    )
                }
            }
        }
        lifecycleScope.launch {
            delay(LAUNCHER_SHOWING_DELAY)
            startActivity(Intent(this@LauncherActivity, MainActivity::class.java))
            finish()
        }
    }

    companion object {
        private const val LAUNCHER_SHOWING_DELAY = 3000L
    }
}