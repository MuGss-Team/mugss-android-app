package com.mugss.mugss.app.authorized.internal.mode.internal.presentation.contract

import androidx.annotation.DrawableRes
import com.mugss.core.compose.MuGssDrawable

enum class ModeImageId(
    val stringId: String,
    @DrawableRes
    val drawableId: Int,
) {
    COMMON(
        "ic_common_mode",
        MuGssDrawable.ic_common_mode
    ),
    ANIME(
        "ic_anime_mode",
        MuGssDrawable.ic_anime_mode
    ),
}

@DrawableRes
internal fun String?.getImageRes(): Int =
    ModeImageId.entries.firstOrNull { it.stringId == this }?.drawableId
        ?: ModeImageId.COMMON.drawableId