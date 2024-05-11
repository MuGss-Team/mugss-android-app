package com.mugss.mugss.app.authorized.internal.mode.internal.presentation.stateholder

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mugss.core.network.api.firebase.modes.Mode
import com.mugss.mugss.app.authorized.internal.mode.internal.data.ModesRepository
import com.mugss.mugss.app.authorized.internal.mode.internal.presentation.contract.ModePresentation
import com.mugss.mugss.app.authorized.internal.mode.internal.presentation.contract.SelectionModeState
import com.mugss.mugss.app.authorized.internal.mode.internal.presentation.contract.getImageRes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class SelectionModeViewModel @Inject constructor(
    private val modesRepository: ModesRepository,
) : ViewModel() {

    var state: SelectionModeState by mutableStateOf(SelectionModeState.Loading)
        private set

    fun fetchModes() = viewModelScope.launch {
        val minimumLoadingDelay = launch {
            delay(2000)
        }
        val result = async {
            modesRepository.geModes()
        }
        minimumLoadingDelay.join()
        result.await().onSuccess {
            state = SelectionModeState.Content(
                modes = it.map(::mapMode).toImmutableList()
            )
        }
    }

    private fun mapMode(mode: Mode) = ModePresentation(
        title = requireNotNull(mode.title),
        description = requireNotNull(mode.description),
        playlistId = requireNotNull(mode.playlistId),
        icon = mode.icon.getImageRes()
    )
}