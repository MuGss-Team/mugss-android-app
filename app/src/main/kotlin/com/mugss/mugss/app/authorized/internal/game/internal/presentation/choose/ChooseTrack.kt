package com.mugss.mugss.app.authorized.internal.game.internal.presentation.choose

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mugss.core.compose.theme.MuGssTheme
import com.mugss.core.compose.theme.typo.shadow.ShadowOffset
import com.mugss.core.compose.theme.typo.shadow.withShadow
import com.mugss.mugss.R
import com.mugss.mugss.app.authorized.internal.game.internal.data.Track
import com.mugss.mugss.app.authorized.internal.game.internal.presentation.contract.GameScreenState
import com.mugss.mugss.app.authorized.internal.game.internal.presentation.statholder.GameVewModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
internal fun SharedTransitionScope.ChooseTrack(
    state: GameScreenState.ChoseTrack,
    onTrackClick: (Track) -> Unit,
    animatedContentScope: AnimatedContentScope,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding(),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TopBar(
            modifier = Modifier.padding(bottom = 30.dp),
            round = state.round
        )
        ProgressGame(progress = state.progress)
        Column(
            verticalArrangement = Arrangement.spacedBy(30.dp)
        ) {
            Spacer(modifier = Modifier)
            val needTransition = state.progress < 0.98f
            TrackCard(
                track = state.roundTracks.first,
                onTrackClick = onTrackClick,
                animatedContentScope = animatedContentScope,
                needTransition = needTransition,
            )
            TrackCard(
                track = state.roundTracks.second,
                onTrackClick = onTrackClick,
                animatedContentScope = animatedContentScope,
                needTransition = needTransition,
            )
            TrackCard(
                track = state.roundTracks.third,
                onTrackClick = onTrackClick,
                animatedContentScope = animatedContentScope,
                needTransition = needTransition,
            )
            Spacer(modifier = Modifier)
        }
    }
}

@Composable
private fun ProgressGame(
    progress: Float,
) {
    val progressAnimation by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(
            durationMillis = GameVewModel.TICK.toInt(),
            easing = LinearEasing,
        ),
        label = "progressAnimation",
    )
    Box(
        modifier = Modifier
            .width(IntrinsicSize.Min)
            .height(IntrinsicSize.Min)
            .clip(MuGssTheme.shapes.roundedCorner20)
            .border(
                color = MuGssTheme.colors.white,
                width = 4.dp,
                shape = MuGssTheme.shapes.roundedCorner20
            )
    ) {
        Image(
            painter = painterResource(id = R.drawable.prorgress_game),
            contentDescription = null,
        )
        Box(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .fillMaxHeight()
                .fillMaxWidth(progressAnimation)
                .background(MuGssTheme.colors.gray2)
        )
    }
}

@Composable
private fun TopBar(
    modifier: Modifier,
    round: Int,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(MuGssTheme.shapes.roundedCornerBottom40)
            .background(MuGssTheme.colors.white)
            .statusBarsPadding()
            .height(104.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = stringResource(id = R.string.round_top_bar, round),
            style = MuGssTheme.typography.titleM.withShadow(
                shadowOffset = ShadowOffset.SMALL
            ),
            color = MuGssTheme.colors.primary,
        )
    }
}