package com.mugss.mugss.app.authorized.internal.game.internal.data

import kotlinx.serialization.Serializable

@Serializable
internal data class Track(
    val imageUrl: String,
    val previewUrl: String,
    val author: String,
    val name: String,
)