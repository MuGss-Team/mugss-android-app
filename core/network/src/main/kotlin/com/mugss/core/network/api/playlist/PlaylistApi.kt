package com.mugss.core.network.api.playlist

interface PlaylistApi {

    suspend fun getPlaylistById(id: String): Result<GetPlaylistByIdResponse>
}