package com.mugss.core.compose.theme.typo.shadow

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

enum class ShadowOffset(
    val x: Dp,
    val y: Dp,
) {
    BIG(
        x = 5.dp,
        y = 10.dp
    ),
    SMALL(
        x = 5.dp,
        y = 5.dp
    )
}