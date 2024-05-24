package com.mugss.mugss.app.authorized.internal.game.internal.presentation.contract

import androidx.compose.runtime.Immutable
import com.mugss.mugss.app.authorized.internal.game.internal.data.Track

@Immutable
internal sealed interface GameScreenState {

    data object Loading : GameScreenState

    sealed interface Content : GameScreenState {
        val round: Int
        val answer: Track
        val isFullScreen: Boolean
        val progress: Float

        fun updateProgress(progress: Float) = when (this) {
            is ChoseTrack -> copy(progress = progress)
            is RoundResult -> copy(progress = progress)
        }
    }

    data class ChoseTrack(
        override val round: Int,
        override val answer: Track,
        override val progress: Float,
        val roundTracks: RoundTracks,
    ) : Content {
        override val isFullScreen: Boolean = false
    }

    data class RoundResult(
        override val round: Int,
        override val answer: Track,
        override val progress: Float,
        val rightAnswer: Boolean,
        val totalScore: Int,
        val roundScore: Int,
    ) : Content {
        override val isFullScreen: Boolean = true
    }
}


@Immutable
internal data class RoundTracks(
    val first: Track,
    val second: Track,
    val third: Track,
)