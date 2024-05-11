package com.mugss.core.network.internal

import com.mugss.core.network.RequestRetryer
import com.mugss.core.network.api.errors.NoSeedValuesException
import com.mugss.core.network.api.spotify.GetPlaylistByIdResponse
import com.mugss.core.network.api.spotify.GetTracksByIdsResponse
import com.mugss.core.network.api.spotify.GetTracksBySearchResponse
import com.mugss.core.network.api.spotify.SpotifyApi
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
) : SpotifyApi {

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
    
    override suspend fun getTracksByQuery(query: String) = withContext(Dispatchers.IO) {
        requestRetrier.sendRetryingRequest {
            getResponseResult<GetTracksBySearchResponse> {
                client.get("search") {
                    parameter("q", query)
                    parameter("type", "track")
                    parameter("market", "US")
                }
            }
        }
    }

    override suspend fun getTracksById(trackIds: List<String>) = withContext(Dispatchers.IO){
        requestRetrier.sendRetryingRequest {
            getResponseResult<GetTracksByIdsResponse> {
                client.get("tracks") {
                    parameter("ids", trackIds.joinToString(","))
                    parameter("market", "US")
                }
            }
        }
    }

    override suspend fun getTracksByRecommendations(
        trackIds: List<String>?,
        artistIds: List<String>?,
        genresTypes: List<String>?,
        targetPopularity: Int?,
        minPopularity: Int?,
        maxPopularity: Int?,
        targetDuration: Int?,
        minDuration: Int?,
        maxDuration: Int?
    ): Result<GetTracksByIdsResponse> = withContext(Dispatchers.IO){
        //Хотя бы дин из первых трех параметров функции должен быть не null
        if (trackIds == null && artistIds == null && genresTypes == null) {
            throw NoSeedValuesException()
        }
        requestRetrier.sendRetryingRequest {
            getResponseResult<GetTracksByIdsResponse> {
                client.get("recommendations") {
                    trackIds?.let { parameter("seed_tracks", it.joinToString(",")) }
                    artistIds?.let { parameter("seed_artists", it.joinToString(",")) }
                    genresTypes?.let { parameter("seed_genres", it.joinToString(",")) }
                    targetPopularity?.let { parameter("target_popularity", it) }
                    minPopularity?.let { parameter("min_popularity", it) }
                    maxPopularity?.let { parameter("max_popularity", it) }
                    targetDuration?.let { parameter("target_duration_ms", it) }
                    minDuration?.let { parameter("min_duration_ms", it) }
                    maxDuration?.let { parameter("max_duration_ms", it) }
                    parameter("market", "US")
                }
            }
        }
    }
}