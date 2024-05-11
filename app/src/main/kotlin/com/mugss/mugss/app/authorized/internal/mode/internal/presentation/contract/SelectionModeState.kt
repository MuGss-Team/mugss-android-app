package com.mugss.mugss.app.authorized.internal.mode.internal.presentation.contract

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable
import kotlinx.collections.immutable.ImmutableList

@Immutable
internal sealed interface SelectionModeState {

    data object Loading : SelectionModeState


    data class Content(
        val modes: ImmutableList<ModePresentation>,
    ) : SelectionModeState
}

internal class ModePresentation(
    val title: String,
    val description: String,
    val playlistId: String,
    @DrawableRes
    val icon: Int,
)

