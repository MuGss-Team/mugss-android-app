package com.mugss.core.compose.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mugss.core.compose.modifier.optionalClickable
import com.mugss.core.compose.theme.MuGssTheme

@Composable
fun MuGssCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    elevation: Dp = 10.dp,
    additionalBottomContent: (@Composable BoxScope.() -> Unit)? = null,
    cardContent: @Composable BoxScope.() -> Unit,
) {
    Box(
        modifier = Modifier.height(IntrinsicSize.Min)
    ) {
        Box(
            modifier = modifier
                .shadow(
                    elevation = elevation,
                    shape = MuGssTheme.shapes.roundedCorner20,
                )
                .background(
                    color = MuGssTheme.colors.backgroundComponents,
                    shape = MuGssTheme.shapes.roundedCorner20,
                ),
            content = cardContent,
        )
        Box(
            modifier = modifier
                .clip(MuGssTheme.shapes.roundedCorner20)
                .optionalClickable(
                    onClick = onClick,
                    indication = rememberRipple()
                ),
            contentAlignment = Alignment.BottomEnd,
        ) {
            additionalBottomContent?.let {
                additionalBottomContent()
            }
        }
    }
}