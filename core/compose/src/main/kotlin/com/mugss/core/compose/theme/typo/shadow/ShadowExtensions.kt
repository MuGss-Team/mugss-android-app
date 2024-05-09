package com.mugss.core.compose.theme.typo.shadow

import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.mugss.core.compose.theme.MuGssTheme

@Composable
fun TextStyle.withShadow(shadowOffset: ShadowOffset): TextStyle {
    val density = LocalDensity.current
    return copy(
        shadow = Shadow(
            offset = Offset(
                with(density) { shadowOffset.x.toPx() },
                with(density) { shadowOffset.y.toPx() }
            ),
            blurRadius = with(density) { 4.dp.toPx() },
            color = MuGssTheme.colors.black.copy(alpha = 0.25f),
        )
    )
}