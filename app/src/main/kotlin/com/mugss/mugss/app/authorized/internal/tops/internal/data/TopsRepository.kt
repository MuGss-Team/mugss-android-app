package com.mugss.mugss.app.authorized.internal.tops.internal.data

import com.mugss.core.network.api.firebase.modes.Mode
import com.mugss.core.network.api.firebase.top.Position

internal interface TopsRepository {

    suspend fun getModes(): Result<List<Mode>>

    suspend fun getTopByMode(modeTitle: String): Result<MutableList<Position>?>
}