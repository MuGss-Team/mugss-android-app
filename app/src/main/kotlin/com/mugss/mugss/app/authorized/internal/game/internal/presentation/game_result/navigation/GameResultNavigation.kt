package com.mugss.mugss.app.authorized.internal.game.internal.presentation.game_result.navigation

import kotlinx.serialization.Serializable

@Serializable
data class GameResultNavigation(
    val scores: Int,
    val countOfGuessed: Int,
    val countOfRounds: Int,
)
