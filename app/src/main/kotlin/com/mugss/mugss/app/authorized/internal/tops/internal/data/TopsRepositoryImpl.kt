package com.mugss.mugss.app.authorized.internal.tops.internal.data

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.Query
import com.mugss.core.network.api.firebase.makeFirebaseRequest
import com.mugss.core.network.api.firebase.modes.Mode
import com.mugss.core.network.api.firebase.modes.ModesStore
import com.mugss.core.network.api.firebase.top.Position
import com.mugss.core.network.api.firebase.top.TopsStore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

internal class TopsRepositoryImpl @Inject constructor(
    @ModesStore
    private val modesStore: CollectionReference,
    @TopsStore
    private val topsStore: CollectionReference
) : TopsRepository {
    override suspend fun getModes(): Result<List<Mode>> = makeFirebaseRequest {
        modesStore.get().await().toObjects(Mode::class.java)
    }

    override suspend fun getTopByMode(modeTitle: String): Result<MutableList<Position>?> = makeFirebaseRequest {
        topsStore
            .document(modeTitle)
            .collection(TOP_COLLECTION)
            .orderBy(ORDER_BY_FIELD, Query.Direction.DESCENDING)
            .get().await()
            .toObjects(Position::class.java)
    }

    override suspend fun saveResultInTopByModeTitle(login: String, score: Long, modeTitle: String): Result<DocumentReference> =
        makeFirebaseRequest {
            topsStore
                .document(modeTitle)
                .collection(TOP_COLLECTION)
                .add(createTopPositionMap(login, score))
                .await()
        }

    private fun createTopPositionMap(login: String, score: Long): Map<String, Any> = mapOf("login" to login, "score" to score)

    companion object {
        private const val TOP_COLLECTION = "top"
        private const val ORDER_BY_FIELD = "score"
    }

}