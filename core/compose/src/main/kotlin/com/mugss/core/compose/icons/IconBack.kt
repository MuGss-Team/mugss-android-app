package com.mugss.core.compose.icons

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.size
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mugss.core.compose.MuGssDrawable

@Composable
fun IconBack(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    IconWithShadow(
        modifier = modifier
            .size(48.dp)
            .clickable(
                onClick = onClick,
                indication = rememberRipple(
                    radius = 24.dp,
                    bounded = false,
                ),
                interactionSource = remember {
                    MutableInteractionSource()
                }
            ),
        iconId = MuGssDrawable.ic_arrow_48
    )
}