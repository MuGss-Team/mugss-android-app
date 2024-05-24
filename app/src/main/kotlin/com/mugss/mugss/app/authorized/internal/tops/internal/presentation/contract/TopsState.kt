package com.mugss.mugss.app.authorized.internal.tops.internal.presentation.contract

import androidx.compose.runtime.Immutable
import com.mugss.core.network.api.firebase.top.Position

@Immutable
internal sealed interface TopsState {

    data object Loading : TopsState

    data class Content(
        val modesTitle: List<String>,
        val positions: List<Position>? = null
    ) : TopsState
}