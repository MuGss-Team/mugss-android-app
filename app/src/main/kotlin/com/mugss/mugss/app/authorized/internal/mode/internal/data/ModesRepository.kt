package com.mugss.mugss.app.authorized.internal.mode.internal.data

import com.mugss.core.network.api.firebase.modes.Mode

internal interface ModesRepository {
    suspend fun geModes(): Result<List<Mode>>
}