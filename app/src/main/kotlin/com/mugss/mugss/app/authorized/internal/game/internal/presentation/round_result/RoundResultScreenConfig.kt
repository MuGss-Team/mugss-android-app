package com.mugss.mugss.app.authorized.internal.game.internal.presentation.round_result

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.mugss.core.compose.MuGssDrawable
import com.mugss.core.compose.theme.MuGssTheme
import com.mugss.mugss.R

enum class RoundResultScreenConfig(
    @StringRes
    val stringId: Int,
    val mainColor: @Composable () -> Color,
    @DrawableRes
    val heartId: Int,
    @DrawableRes
    val duckId: Int,
) {
    SUCCESS(
        stringId = R.string.right,
        mainColor = { MuGssTheme.colors.success },
        heartId = MuGssDrawable.ic_heart,
        duckId = MuGssDrawable.ic_normal_duck,
    ),
    FAILURE(
        stringId = R.string.wrong,
        mainColor = { MuGssTheme.colors.failure },
        heartId = MuGssDrawable.ic_broken_heart,
        duckId = MuGssDrawable.ic_sad_duck,
    )
}