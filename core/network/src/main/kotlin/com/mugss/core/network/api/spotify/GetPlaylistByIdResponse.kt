package com.mugss.core.network.api.spotify

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetPlaylistByIdResponse(
    @SerialName("items")
    val items: List<PlaylistTrackDataModel>
)

@Serializable
data class PlaylistTrackDataModel(
    @SerialName("track")
    val track: TrackDataModel
)