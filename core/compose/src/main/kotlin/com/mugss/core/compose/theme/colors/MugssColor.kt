package com.mugss.core.compose.theme.colors

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

internal val LocalMuGssColor = staticCompositionLocalOf<MugssColor> {
    error("No colors provided")
}

data class MugssColor(
    val primary: Color,
    val white: Color,
    val black: Color,
    val gray: Color,
    val success: Color,
    val failure: Color,
)

fun lightColor() = MugssColor(
    primary = Color(0xFF468FAF),
    white = Color(0xFFFFFFFF),
    black = Color(0xFF000000),
    gray = Color(0xFFBCBCBC),
    success = Color(0xFF58AFAA),
    failure = Color(0xFF9446AF),
)