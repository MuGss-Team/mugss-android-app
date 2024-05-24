package com.mugss.mugss.app.authorized.internal.tops.internal.presentation.stateholder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mugss.mugss.app.authorized.internal.tops.internal.data.TopsRepository
import com.mugss.mugss.app.authorized.internal.tops.internal.presentation.contract.TopsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class TopsViewModel @Inject constructor(
    private val topsRepository: TopsRepository
): ViewModel() {

    private var _state: MutableStateFlow<TopsState> = MutableStateFlow(TopsState.Loading)
    val state: Flow<TopsState> = _state

    fun getModes() {
        viewModelScope.launch {
            val result = async {
                topsRepository.getModes()
            }
            result.await().onSuccess { modes ->
                _state.value = TopsState.Content(modesTitle = modes.map { it.title ?: "no_name" })
            }
        }
    }

    fun getTopByMode(modeTitle: String) {
        viewModelScope.launch {
            val result = async {
                topsRepository.getTopByMode(modeTitle)
            }
            result.await().onSuccess {
                if (_state.value is TopsState.Content && it != null) {
                    _state.value = (_state.value as TopsState.Content).copy(positions = it.toList())
                }
            }
        }
    }
}