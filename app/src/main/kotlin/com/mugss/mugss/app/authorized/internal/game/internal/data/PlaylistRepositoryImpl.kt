package com.mugss.mugss.app.authorized.internal.game.internal.data

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.mugss.core.network.api.firebase.makeFirebaseRequest
import com.mugss.core.network.api.firebase.playlist.Playlist
import com.mugss.core.network.api.firebase.playlist.PlaylistStore
import com.mugss.core.network.api.firebase.playlist.PlaylistType
import com.mugss.core.network.api.firebase.playlist.SpotifyPlaylist
import com.mugss.core.network.api.spotify.PlaylistTrackDataModel
import com.mugss.core.network.api.spotify.SpotifyApi
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

internal class PlaylistRepositoryImpl @Inject constructor(
    @PlaylistStore
    private val playListStore: CollectionReference,
    private val playlistApi: SpotifyApi,
) : PlaylistRepository {

    override suspend fun getTracksById(id: String): Result<List<Track>> = makeFirebaseRequest {
        val document = playListStore.document(id).get().await()
        requireNotNull(document)
        val playlistType = requireNotNull(document.toObject(Playlist::class.java)?.type)
        when (playlistType) {
            PlaylistType.SPOTIFY.string -> getSpotifyPlaylistTracks(document)
            PlaylistType.CUSTOM.string -> TODO()
            else -> throw UnsupportedTypePlaylistException()
        }
    }

    private suspend fun getSpotifyPlaylistTracks(document: DocumentSnapshot): List<Track> {
        val spotifyPlaylistId =
            requireNotNull(document.toObject(SpotifyPlaylist::class.java)?.spotifyPlaylistId)
        return playlistApi
            .getPlaylistById(spotifyPlaylistId)
            .getOrThrow()
            .items
            .mapIndexedNotNull(::mapToTrack)
            .take(60)
    }

    private fun mapToTrack(index: Int, tracksDataModel: PlaylistTrackDataModel): Track? =
        try {
            Track(
                id = index,
                previewUrl = requireNotNull(tracksDataModel.track.previewUrl),
                author = tracksDataModel.track.artists.first().name,
                name = tracksDataModel.track.name,
                imageUrl = tracksDataModel.track.album.images.first().url
            )
        } catch (t: Throwable) {
            null
        }
}