package com.mugss.mugss.app.authorized.internal.game.internal.presentation.game_result.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.mugss.core.navigation.AuthorizedNavFactory
import com.mugss.mugss.app.authorized.internal.game.internal.presentation.game_result.GameResultScreen
import com.mugss.mugss.app.authorized.internal.home.api.navigation.Home
import javax.inject.Inject

class GameResultNavFactory @Inject constructor() : AuthorizedNavFactory {

    override fun create(navGraphBuilder: NavGraphBuilder, navController: NavController) {
        navGraphBuilder.composable<GameResultNavigation> {
            val gameResultNavigation = it.toRoute<GameResultNavigation>()
            GameResultScreen(
                scores = gameResultNavigation.scores,
                countOfGuessed = gameResultNavigation.countOfGuessed,
                countOfRounds = gameResultNavigation.countOfRounds,
                onHomeNavigate = {
                    navController.navigate(Home) {
                        popUpTo<Home>()
                        launchSingleTop = true
                    }
                },
            )
        }
    }
}