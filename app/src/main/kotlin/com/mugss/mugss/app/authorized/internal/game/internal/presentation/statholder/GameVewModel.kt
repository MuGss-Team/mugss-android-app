package com.mugss.mugss.app.authorized.internal.game.internal.presentation.statholder

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mugss.mugss.app.authorized.internal.game.internal.data.PlaylistRepository
import com.mugss.mugss.app.authorized.internal.game.internal.data.Track
import com.mugss.mugss.app.authorized.internal.game.internal.presentation.contract.GameScreenState
import com.mugss.mugss.app.authorized.internal.game.internal.presentation.contract.RoundTracks
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class GameVewModel @Inject constructor(
    private val playlistRepository: PlaylistRepository,
) : ViewModel() {

    private val internalState = mutableStateListOf<Track>()

    var state: GameScreenState by mutableStateOf(
        GameScreenState.Loading
    )
        private set


    fun fetchTracks(id: String) = viewModelScope.launch {
        playlistRepository.getTracksById(id).onSuccess {
            internalState.addAll(
                it.shuffled()
            )
            onNewRound()
        }
    }

    fun onTrackClick(track: Track) {

    }

    private fun onNewRound() {
        if (internalState.size < 3) {

        } else {
            state = GameScreenState.Content(
                round = (state as? GameScreenState.Content)?.round ?: 1,
                roundTracks = RoundTracks(
                    first = internalState[0],
                    second = internalState[1],
                    third = internalState[2]
                ),
                answer = internalState[2],
                fullScreen = false,
            )
            internalState.removeRange(0, 2)
        }
    }
}