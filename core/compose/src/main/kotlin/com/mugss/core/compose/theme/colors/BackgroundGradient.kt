package com.mugss.core.compose.theme.colors

import androidx.compose.foundation.background
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.mugss.core.compose.theme.MuGssTheme

fun Modifier.radialGradientPrimaryBackground() = composed {
    background(
        Brush.radialGradient(
            0.51f to MuGssTheme.colors.secondary,
            0.82f to MuGssTheme.colors.primary,
            radius = with(LocalDensity.current) { (LocalConfiguration.current.screenHeightDp / 1.7).dp.toPx() }
        )
    )
}