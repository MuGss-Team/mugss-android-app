package com.mugss.core.compose.icons

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mugss.core.compose.theme.MuGssTheme
import com.mugss.core.compose.theme.typo.shadow.ShadowOffset

@Composable
fun IconWithShadow(
    @DrawableRes
    iconId: Int,
    modifier: Modifier = Modifier,
) {
    val painter = painterResource(id = iconId)
    Box {
        Icon(
            modifier = modifier,
            painter = painter,
            contentDescription = null,
            tint = MuGssTheme.colors.white,
        )
        Icon(
            modifier = modifier
                .offset(
                    x = ShadowOffset.SMALL.x,
                    y = ShadowOffset.SMALL.y
                )
                .blur(4.dp),
            painter = painter,
            contentDescription = null,
            tint = MuGssTheme.colors.black.copy(alpha = 0.25f),
        )
    }
}