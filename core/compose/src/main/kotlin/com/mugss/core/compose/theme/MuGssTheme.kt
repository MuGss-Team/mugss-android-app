package com.mugss.core.compose.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import com.mugss.core.compose.theme.colors.LocalMuGssColor
import com.mugss.core.compose.theme.colors.MugssColors
import com.mugss.core.compose.theme.colors.lightColor
import com.mugss.core.compose.theme.shapes.MugssShapes
import com.mugss.core.compose.theme.typo.LocalMuGssTypography
import com.mugss.core.compose.theme.typo.MuGssTypography
import com.mugss.core.compose.theme.typo.typo

object MuGssTheme {

    val colors: MugssColors
        @Composable
        get() = LocalMuGssColor.current

    val typography: MuGssTypography
        @Composable
        get() = LocalMuGssTypography.current

    val shapes = MugssShapes()
}

@Composable
fun MuGssTheme(
    content: @Composable () -> Unit,
) {
    val color = remember {
        lightColor()
    }
    val typography = remember {
        typo()
    }
    CompositionLocalProvider(
        LocalMuGssTypography provides typography,
        LocalMuGssColor provides color,
        content = content,
    )
}