package com.mugss.mugss.app.authorized.internal.game.internal.presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import com.mugss.core.compose.loading.LoadingScreen
import com.mugss.core.compose.theme.MuGssTheme
import com.mugss.mugss.app.authorized.internal.game.internal.data.Track
import com.mugss.mugss.app.authorized.internal.game.internal.presentation.choose.ChooseTrack
import com.mugss.mugss.app.authorized.internal.game.internal.presentation.contract.GameScreenState
import com.mugss.mugss.app.authorized.internal.game.internal.presentation.round_result.RoundResultScreen
import com.mugss.mugss.app.authorized.internal.game.internal.presentation.round_result.RoundResultScreenConfig
import com.mugss.mugss.app.authorized.internal.game.internal.presentation.statholder.GameVewModel

@Composable
internal fun GameScreen(
    playlistId: String,
    onNavigate: (Any) -> Unit,
    gameVewModel: GameVewModel = hiltViewModel(),
) {
    val state = gameVewModel.screenState
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(key1 = Unit) {
        gameVewModel.fetchTracks(playlistId)
    }

    LaunchedEffect(key1 = Unit) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
            gameVewModel.navigationFlow.collect {
                onNavigate(it)
            }
        }
    }

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_RESUME -> gameVewModel.onResume()
                Lifecycle.Event.ON_PAUSE -> gameVewModel.onPause()
                else -> Unit
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MuGssTheme.colors.primary)
    ) {
        AnimatedContent(
            targetState = state,
            contentKey = { it is GameScreenState.Loading },
            transitionSpec = { fadeIn() togetherWith fadeOut() },
            label = "gameScreenCrossfade"
        ) { state ->
            when (state) {
                is GameScreenState.Loading -> LoadingScreen()
                is GameScreenState.Content -> {
                    Content(
                        state = state,
                        onTrackClick = gameVewModel::onTrackClick,
                        onNextRound = gameVewModel::onNewRound,
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
private fun Content(
    state: GameScreenState.Content,
    onTrackClick: (Track) -> Unit,
    onNextRound: () -> Unit,
) {
    SharedTransitionLayout(Modifier.fillMaxSize()) {
        AnimatedContent(
            targetState = state,
            contentKey = { it.isFullScreen },
            label = "resultAnimation",
            transitionSpec = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Start
                ) togetherWith slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Start
                )
            }
        ) {
            when (it) {
                is GameScreenState.ChoseTrack -> ChooseTrack(
                    state = it,
                    onTrackClick = onTrackClick,
                    animatedContentScope = this@AnimatedContent,
                )

                is GameScreenState.RoundResult -> RoundResultScreen(
                    score = it.roundScore,
                    totalScore = it.totalScore,
                    roundResultScreenConfig = if (it.rightAnswer) RoundResultScreenConfig.SUCCESS else RoundResultScreenConfig.FAILURE,
                    track = it.answer,
                    onNextRound = onNextRound,
                    animatedContentScope = this@AnimatedContent,
                    progress = state.progress,
                )
            }
        }
    }
}
