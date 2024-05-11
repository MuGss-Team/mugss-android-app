package com.mugss.core.network.api.spotify

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetTracksBySearchResponse(
    @SerialName("tracks")
    val tracks: SearchTrackDataModel
)

@Serializable
data class SearchTrackDataModel(
    @SerialName("items")
    val items: List<TrackDataModel>
)