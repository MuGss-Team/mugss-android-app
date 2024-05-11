package com.mugss.core.network.api.spotify

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetTracksByIdsResponse (
    @SerialName("tracks")
    val tracks: List<TrackDataModel>
)