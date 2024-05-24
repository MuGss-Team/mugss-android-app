package com.mugss.core.compose.theme.colors

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

internal val LocalMuGssColor = staticCompositionLocalOf<MugssColors> {
    error("No colors provided")
}

data class MugssColors(
    val primary: Color,
    val secondary: Color,
    val backgroundComponents: Color,
    val white: Color,
    val black: Color,
    val gray: Color,
    val gray2: Color,
    val success: Color,
    val failure: Color,
)

fun lightColor() = MugssColors(
    primary = Color(0xFF468FAF),
    secondary = Color(0xFF575EAE),
    backgroundComponents = Color(0xFF3B7D9A),
    white = Color(0xFFFFFFFF),
    black = Color(0xFF000000),
    gray = Color(0xFFBCBCBC),
    gray2 = Color(0xFFE1E1E1),
    success = Color(0xFF58AFAA),
    failure = Color(0xFF9446AF),
)