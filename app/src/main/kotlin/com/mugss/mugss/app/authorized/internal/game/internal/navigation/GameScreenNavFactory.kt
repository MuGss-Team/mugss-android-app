package com.mugss.mugss.app.authorized.internal.game.internal.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.mugss.core.navigation.AuthorizedNavFactory
import com.mugss.mugss.app.authorized.internal.game.api.navigation.Game
import com.mugss.mugss.app.authorized.internal.game.internal.presentation.GameScreen
import javax.inject.Inject

class GameScreenNavFactory @Inject constructor() : AuthorizedNavFactory {

    override fun create(navGraphBuilder: NavGraphBuilder, navController: NavController) {
        navGraphBuilder.composable<Game> {
            GameScreen(it.toRoute<Game>().playListId)
        }
    }
}