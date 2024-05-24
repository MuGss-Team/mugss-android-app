package com.mugss.mugss.app.authorized.internal.game.internal.presentation.statholder

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.mugss.mugss.app.authorized.internal.game.internal.data.PlaylistRepository
import com.mugss.mugss.app.authorized.internal.game.internal.data.Track
import com.mugss.mugss.app.authorized.internal.game.internal.presentation.contract.GameScreenState
import com.mugss.mugss.app.authorized.internal.game.internal.presentation.contract.RoundTracks
import com.mugss.mugss.app.authorized.internal.game.internal.presentation.game_result.navigation.GameResultNavigation
import com.mugss.mugss.app.utils.Timer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.roundToInt
import kotlin.random.Random
import kotlin.random.nextInt

@HiltViewModel
internal class GameVewModel @Inject constructor(
    private val playlistRepository: PlaylistRepository,
    private val exoPlayer: ExoPlayer,
) : ViewModel() {

    private val internalStateTracks = mutableStateListOf<Track>()
    private var multiplier by mutableFloatStateOf(1f)
    private var scores by mutableIntStateOf(0)
    private var guessedTracks by mutableIntStateOf(0)

    private val _navigationFlow = MutableSharedFlow<Any>(
        onBufferOverflow = BufferOverflow.DROP_OLDEST,
        extraBufferCapacity = 1,
    )
    val navigationFlow = _navigationFlow.asSharedFlow()

    var screenState: GameScreenState by mutableStateOf(
        GameScreenState.Loading
    )
        private set

    private val timer = Timer(
        scope = viewModelScope,
        duration = ROUND_TIME,
        onTick = { timeLeft ->
            updateContentState {
                it.updateProgress(timeLeft.toFloat() / ROUND_TIME)
            }
        },
        onFinish = {
            onTrackClick(null)
        },
        period = TICK,
    )


    fun fetchTracks(id: String) = viewModelScope.launch {
        playlistRepository.getTracksById(id).onSuccess {
            internalStateTracks.addAll(
                it.shuffled()
            )
            onNewRound()
        }
    }

    fun onTrackClick(track: Track?) {
        updateContentState { state ->
            val isWin = track == state.answer
            val roundScore = if (isWin) {
                guessedTracks++
                multiplier *= 1.1f
                (ROUND_WIN_DEFAULT_SCORE * multiplier).roundToInt()
            } else {
                multiplier = 1f
                -ROUND_LOOSE_DEFAULT_SCORE
            }
            scores += roundScore
            GameScreenState.RoundResult(
                rightAnswer = isWin,
                round = state.round,
                answer = state.answer,
                progress = state.progress,
                totalScore = scores,
                roundScore = roundScore,
            )
        }
    }

    fun onNewRound() {
        if (internalStateTracks.size < 3) {
            _navigationFlow.tryEmit(
                GameResultNavigation(
                    scores = scores,
                    countOfGuessed = guessedTracks,
                    countOfRounds = (screenState as? GameScreenState.Content)?.round ?: 0
                )
            )
        } else {
            val answer = internalStateTracks[Random.nextInt(0..2)]
            screenState = GameScreenState.ChoseTrack(
                round = ((screenState as? GameScreenState.Content)?.round ?: 0) + 1,
                roundTracks = RoundTracks(
                    first = internalStateTracks[0],
                    second = internalStateTracks[1],
                    third = internalStateTracks[2]
                ),
                progress = 1f,
                answer = answer,
            )
            timer.reset()
            exoPlayer.onNewTrack(answer.previewUrl)
            internalStateTracks.removeRange(0, 3)
        }
    }


    fun onPause() {
        timer.pause()
        exoPlayer.pause()
    }

    fun onResume() {
        timer.resume()
        exoPlayer.play()
    }

    private fun ExoPlayer.onNewTrack(url: String) {
        if (mediaItemCount > 0) {
            removeMediaItem(currentMediaItemIndex)
        }
        setMediaItem(
            MediaItem.Builder()
                .setUri(url)
                .setClippingConfiguration(
                    MediaItem.ClippingConfiguration.Builder()
                        .setEndPositionMs(ROUND_TIME)
                        .build()
                ).build()
        )
        prepare()
        playWhenReady = true
        val listener = object : Player.Listener {
            override fun onIsPlayingChanged(isPlaying: Boolean) {
                if (isPlaying) {
                    timer.resume()
                    removeListener(this)
                }
            }
        }
        addListener(listener)
    }

    private fun updateContentState(
        update: (GameScreenState.Content) -> GameScreenState.Content,
    ) {
        val castedState = (screenState as? GameScreenState.Content)
        castedState?.let {
            screenState = update(it)
        }
    }

    override fun onCleared() {
        exoPlayer.release()
        super.onCleared()
    }

    companion object {
        private const val ROUND_WIN_DEFAULT_SCORE = 100
        private const val ROUND_LOOSE_DEFAULT_SCORE = 50
        private const val ROUND_TIME = 20000L
        const val TICK = 100L
        const val ROUND_TIME_SECONDS = (ROUND_TIME / 1000).toInt()
    }
}