package com.mugss.mugss.app.authorized.internal.game.internal.data

internal interface PlaylistRepository {
    suspend fun getTracksById(id: String): Result<List<Track>>
}