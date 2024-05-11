package com.mugss.mugss.app.authorized.internal.mode.internal.data

import com.google.firebase.firestore.CollectionReference
import com.mugss.core.network.api.firebase.makeFirebaseRequest
import com.mugss.core.network.api.firebase.modes.Mode
import com.mugss.core.network.api.firebase.modes.ModesStore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

internal class ModesRepositoryImpl @Inject constructor(
    @ModesStore
    private val modesStore: CollectionReference,
) : ModesRepository {

    override suspend fun geModes(): Result<List<Mode>> = makeFirebaseRequest {
        modesStore.get().await().toObjects(Mode::class.java)
    }
}