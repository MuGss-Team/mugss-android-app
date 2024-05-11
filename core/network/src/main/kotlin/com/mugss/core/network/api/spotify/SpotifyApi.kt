package com.mugss.core.network.api.spotify

interface SpotifyApi {

    suspend fun getPlaylistById(id: String): Result<GetPlaylistByIdResponse>

    suspend fun getTracksByQuery(query: String): Result<GetTracksBySearchResponse>

    suspend fun getTracksById(trackIds: List<String>): Result<GetTracksByIdsResponse>

    suspend fun getTracksByRecommendations(
        trackIds: List<String>? = null,
        artistIds: List<String>? = null,
        genresTypes: List<String>? = null,
        targetPopularity: Int? = null,
        minPopularity: Int? = null,
        maxPopularity: Int? = null,
        targetDuration: Int? = null,
        minDuration: Int? = null,
        maxDuration: Int? = null,
    ): Result<GetTracksByIdsResponse>
}