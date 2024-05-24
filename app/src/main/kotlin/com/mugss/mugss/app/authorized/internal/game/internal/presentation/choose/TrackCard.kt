package com.mugss.mugss.app.authorized.internal.game.internal.presentation.choose

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.BoundsTransform
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.mugss.core.compose.cards.MuGssCard
import com.mugss.core.compose.theme.MuGssTheme
import com.mugss.core.compose.theme.typo.shadow.ShadowOffset
import com.mugss.core.compose.theme.typo.shadow.withShadow
import com.mugss.mugss.app.authorized.internal.game.internal.data.Track

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
internal fun SharedTransitionScope.TrackCard(
    modifier: Modifier = Modifier,
    track: Track,
    onTrackClick: (Track) -> Unit,
    animatedContentScope: AnimatedContentScope,
    needTransition: Boolean = false,
) {
    val bounds = BoundsTransform { initialBounds, targetBounds ->
        keyframes {
            durationMillis = 1000
            initialBounds at 0 using LinearEasing
            targetBounds at 1000
        }
    }
    MuGssCard(
        modifier = modifier
            .then(
                if (needTransition) Modifier.sharedBounds(
                    rememberSharedContentState(key = track),
                    animatedVisibilityScope = animatedContentScope,
                    boundsTransform = bounds,
                ) else Modifier
            )
            .fillMaxWidth()
            .height(160.dp)
            .padding(horizontal = 30.dp),
        onClick = {
            onTrackClick(track)
        },
    ) {
        Row(
            Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .then(
                        if (needTransition) Modifier.sharedElement(
                            rememberSharedContentState(key = track.imageUrl + track.id),
                            animatedVisibilityScope = animatedContentScope,
                            boundsTransform = bounds,
                        ) else Modifier
                    )
                    .size(132.dp),
                model = track.imageUrl,
                contentDescription = null
            )
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    modifier = Modifier
                        .then(
                            if (needTransition) Modifier.sharedElement(
                                rememberSharedContentState(key = track.name + track.id),
                                animatedVisibilityScope = animatedContentScope,
                                boundsTransform = bounds,
                            ) else Modifier
                        )
                        .fillMaxWidth(),
                    text = track.name,
                    style = MuGssTheme.typography.bodyL.copy(
                        lineHeight = 25.sp
                    ).withShadow(shadowOffset = ShadowOffset.SMALL),
                    color = MuGssTheme.colors.white,
                    textAlign = TextAlign.Center,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    modifier = Modifier
                        .then(
                            if (needTransition) Modifier.sharedElement(
                                rememberSharedContentState(key = track.author + track.id),
                                animatedVisibilityScope = animatedContentScope,
                                boundsTransform = bounds,
                            ) else Modifier
                        )
                        .fillMaxWidth(),
                    text = track.author,
                    style = MuGssTheme.typography.bodyS.withShadow(shadowOffset = ShadowOffset.SMALL),
                    color = MuGssTheme.colors.white,
                    textAlign = TextAlign.Center,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}