package com.mugss.mugss.app.authorized.internal.game.internal.data

import androidx.compose.runtime.Immutable
import kotlinx.serialization.Serializable

@Serializable
@Immutable
internal data class Track(
    val id: Int,
    val imageUrl: String,
    val previewUrl: String,
    val author: String,
    val name: String,
)