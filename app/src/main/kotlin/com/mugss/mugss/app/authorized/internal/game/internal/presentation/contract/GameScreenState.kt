package com.mugss.mugss.app.authorized.internal.game.internal.presentation.contract

import com.mugss.mugss.app.authorized.internal.game.internal.data.Track


internal sealed interface GameScreenState {

    data object Loading : GameScreenState

    data class Content(
        val round: Int,
        val roundTracks: RoundTracks,
        val answer: Track,
        val fullScreen: Boolean,
    ) : GameScreenState
}

internal data class RoundTracks(
    val first: Track,
    val second: Track,
    val third: Track,
)