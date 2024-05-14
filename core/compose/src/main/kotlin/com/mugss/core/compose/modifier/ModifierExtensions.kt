package com.mugss.core.compose.modifier

import androidx.compose.foundation.Indication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed


fun Modifier.optionalClickable(
    onClick: (() -> Unit)? = null,
    indication: Indication? = null,
    interactionSource: MutableInteractionSource? = null
) = composed {
    onClick?.let {
        clickable(
            onClick = it,
            interactionSource = interactionSource ?: remember {
                MutableInteractionSource()
            },
            indication = indication,
        )
    } ?: this
}