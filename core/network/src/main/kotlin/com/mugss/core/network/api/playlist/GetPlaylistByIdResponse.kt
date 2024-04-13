package com.mugss.core.network.api.playlist;

import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;

@Serializable
data class GetPlaylistByIdResponse(
    @SerialName("items")
    val items: List<TracksDataModel>
)

@Serializable
data class TracksDataModel(
    @SerialName("track")
    val track: TrackDataModel
)

@Serializable
data class TrackDataModel(
    @SerialName("id")
    val id: String,
    @SerialName("album")
    val album: AlbumDataModel,
    @SerialName("artists")
    val artists: List<ArtistDataModel> = listOf(),
    @SerialName("name")
    val name: String = "",
    @SerialName("preview_url")
    val previewUrl: String? = null,
    @SerialName("popularity")
    val popularity: Int? = null,
    @SerialName("duration_ms")
    val durationMs: Int? = null,
    @SerialName("explicit")
    val explicit: Boolean? = null,
)

@Serializable
data class AlbumDataModel(
    @SerialName("id")
    val id: String,
    @SerialName("images")
    val images: List<ImageDataModel> = listOf(),
    @SerialName("release_date")
    val releaseDate: String,
    @SerialName("release_date_precision")
    val releaseDatePrecision: String,
)

@Serializable
data class ImageDataModel(
    @SerialName("height")
    val height: Int = 0,
    @SerialName("url")
    val url: String = "",
    @SerialName("width")
    val width: Int = 0
)

@Serializable
data class ArtistDataModel(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
    @SerialName("popularity")
    val popularity: Int? = null,
)