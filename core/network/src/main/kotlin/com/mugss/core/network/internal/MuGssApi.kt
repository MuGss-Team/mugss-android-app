package com.mugss.core.network.internal

import com.mugss.core.network.RequestRetryer
import com.mugss.core.network.api.playlist.GetPlaylistByIdResponse
import com.mugss.core.network.api.playlist.PlaylistApi
import com.mugss.core.network.internal.di.SpotifyClient
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class MuGssApi @Inject constructor(
    @SpotifyClient
    private val client: HttpClient,
    private val requestRetrier: RequestRetryer,
) : PlaylistApi {

    override suspend fun getPlaylistById(id: String) = withContext(Dispatchers.IO) {
        requestRetrier.sendRetryingRequest {
            getResponseResult<GetPlaylistByIdResponse> {
                client.get("playlists/$id/tracks") {
                    parameter(
                        "fields",
                        "items(track(" +
                                "album(id, images, release_date,release_date_precision)," +
                                " preview_url," +
                                " artists(id, name, popularity)," +
                                " name," +
                                " id," +
                                " popularity," +
                                " duration_ms, " +
                                "explicit" +
                                "))"
                    )
                    parameter("market", "US")
                }
            }
        }
    }
}