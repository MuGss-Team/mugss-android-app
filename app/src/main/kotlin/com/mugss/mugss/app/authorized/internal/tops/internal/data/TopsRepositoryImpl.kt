package com.mugss.mugss.app.authorized.internal.tops.internal.data

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.Query
import com.mugss.core.network.api.firebase.makeFirebaseRequest
import com.mugss.core.network.api.firebase.modes.Mode
import com.mugss.core.network.api.firebase.modes.ModesStore
import com.mugss.core.network.api.firebase.top.Position
import com.mugss.core.network.api.firebase.top.TopsStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
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

    override suspend fun saveResultInTopByModeTitle(login: String, score: Long, modeTitle: String): Result<Boolean> = withContext(Dispatchers.IO) {
            val result = async {
                checkShouldAddNewPosition(login, score, modeTitle)
            }
            result.await().onSuccess { shouldAdd ->
                if (shouldAdd) {
                    topsStore
                        .document(modeTitle)
                        .collection(TOP_COLLECTION)
                        .add(createTopPositionMap(login, score))
                        .await()
                }
            }
        }

    private suspend fun checkShouldAddNewPosition(login: String, score: Long, modeTitle: String): Result<Boolean> = makeFirebaseRequest {
        val collection = topsStore.document(modeTitle).collection(TOP_COLLECTION)
        val positions = collection
            .whereEqualTo(LOGIN_FIELD, login)
            .orderBy(ORDER_BY_FIELD, Query.Direction.DESCENDING)
            .get().await().toObjects(Position::class.java)
        if (positions.isNotEmpty()) {
            if (positions[0] != null && positions[0].score != null) {
                if (positions[0].score!! < score) {
                    collection
                        .whereEqualTo(LOGIN_FIELD, login).get().await()
                        .documents[0].reference.set(createTopPositionMap(login, score)).await()
                    false
                } else {
                    false
                    }
            } else {
                true
            }
        } else {
            true
        }
    }

    private fun createTopPositionMap(login: String, score: Long): Map<String, Any> = mapOf("login" to login, "score" to score)

    companion object {
        private const val TOP_COLLECTION = "top"
        private const val LOGIN_FIELD = "login"
        private const val ORDER_BY_FIELD = "score"
    }

}