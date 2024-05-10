package com.mugss.core.compose.modifier

import androidx.compose.foundation.clickable
import androidx.compose.ui.Modifier

fun Modifier.optionalClickable(
    onClick: (() -> Unit)? = null,
) = onClick?.let { clickable(onClick = onClick) } ?: this