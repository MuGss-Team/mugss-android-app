package com.mugss.mugss.app.authorized.internal.game.internal.presentation

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.mugss.core.compose.cards.MuGssCard
import com.mugss.core.compose.loading.LoadingScreen
import com.mugss.core.compose.theme.MuGssTheme
import com.mugss.core.compose.theme.typo.shadow.ShadowOffset
import com.mugss.core.compose.theme.typo.shadow.withShadow
import com.mugss.mugss.R
import com.mugss.mugss.app.authorized.internal.game.internal.data.Track
import com.mugss.mugss.app.authorized.internal.game.internal.presentation.contract.GameScreenState
import com.mugss.mugss.app.authorized.internal.game.internal.presentation.statholder.GameVewModel

@Composable
internal fun GameScreen(
    playlistId: String,
    gameVewModel: GameVewModel = hiltViewModel(),
) {
    val state = gameVewModel.state

    LaunchedEffect(key1 = Unit) {
        gameVewModel.fetchTracks(playlistId)
    }

    Crossfade(
        targetState = state,
        label = "GameScreenCrossfade"
    ) {
        when (it) {
            is GameScreenState.Loading -> LoadingScreen()
            is GameScreenState.Content -> Content(
                state = it,
                onTrackClick = gameVewModel::onTrackClick
            )
        }
    }
}

@Composable
private fun Content(
    state: GameScreenState.Content,
    onTrackClick: (Track) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MuGssTheme.colors.primary)
    ) {
        Box {
            TopBar(
                modifier = Modifier.padding(bottom = 30.dp),
                round = state.round
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MuGssTheme.colors.primary.copy(alpha = 0.2f))
                    .statusBarsPadding()
            )
        }
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(30.dp)
        ) {
            Spacer(modifier = Modifier)
            TrackCard(
                track = state.roundTracks.first,
                onTrackClick = onTrackClick,
            )
            TrackCard(
                track = state.roundTracks.second,
                onTrackClick = onTrackClick,
            )
            TrackCard(
                track = state.roundTracks.third,
                onTrackClick = onTrackClick,
            )
            Spacer(modifier = Modifier)
        }
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

@Composable
private fun TrackCard(
    modifier: Modifier = Modifier,
    track: Track,
    onTrackClick: (Track) -> Unit,
) {
    MuGssCard(
        modifier = modifier
            .fillMaxWidth()
            .height(160.dp)
            .padding(horizontal = 30.dp),
        onClick = { onTrackClick(track) },
    ) {
        Row(
            Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            AsyncImage(
                modifier = Modifier.size(132.dp),
                model = track.imageUrl,
                contentDescription = null
            )
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = track.name,
                    style = MuGssTheme.typography.bodyL.copy(
                        lineHeight = 25.sp
                    ).withShadow(shadowOffset = ShadowOffset.SMALL),
                    color = MuGssTheme.colors.white,
                    textAlign = TextAlign.Center,
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = track.author,
                    style = MuGssTheme.typography.bodyS.withShadow(shadowOffset = ShadowOffset.SMALL),
                    color = MuGssTheme.colors.white,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }

}