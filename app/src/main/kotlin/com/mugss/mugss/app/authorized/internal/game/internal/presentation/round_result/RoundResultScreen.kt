package com.mugss.mugss.app.authorized.internal.game.internal.presentation.round_result

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mugss.core.compose.MuGssDrawable
import com.mugss.core.compose.icons.IconWithShadow
import com.mugss.core.compose.theme.MuGssTheme
import com.mugss.core.compose.theme.typo.shadow.ShadowOffset
import com.mugss.core.compose.theme.typo.shadow.withShadow
import com.mugss.mugss.R
import com.mugss.mugss.app.authorized.internal.game.internal.data.Track
import com.mugss.mugss.app.authorized.internal.game.internal.presentation.statholder.GameVewModel
import kotlin.math.roundToInt

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
internal fun SharedTransitionScope.RoundResultScreen(
    score: Int,
    totalScore: Int,
    roundResultScreenConfig: RoundResultScreenConfig,
    track: Track,
    onNextRound: () -> Unit,
    animatedContentScope: AnimatedContentScope,
    progress: Float,
) {
    val mainColor = roundResultScreenConfig.mainColor()
    Column(
        Modifier
            .sharedBounds(
                rememberSharedContentState(key = track),
                animatedVisibilityScope = animatedContentScope,
            )
            .fillMaxSize()
            .background(mainColor)
            .safeDrawingPadding(),
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 30.dp,
                    bottom = 20.dp
                ),
            text = stringResource(id = roundResultScreenConfig.stringId),
            style = MuGssTheme.typography.titleM.withShadow(shadowOffset = ShadowOffset.SMALL),
            textAlign = TextAlign.Center,
            color = MuGssTheme.colors.white,
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .clip(MuGssTheme.shapes.roundedCornerTop20)
                .background(MuGssTheme.colors.white),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            TrackContent(
                track = track,
                animatedContentScope = animatedContentScope,
                mainColor = mainColor,
                progress = progress,
            )

            BottomContent(
                totalScore = totalScore,
                score = score,
                heartIconId = roundResultScreenConfig.heartId,
                duckIconId = roundResultScreenConfig.duckId,
                mainColor = mainColor,
                onNextRound = onNextRound,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ColumnScope.ControlsContent(
    progress: Float,
    mainColor: Color,
) {
    val progressAnimation by animateFloatAsState(
        targetValue = 1 - (progress),
        animationSpec = tween(
            durationMillis = GameVewModel.TICK.toInt(),
            easing = LinearEasing,
        ),
    )

    Row(
        modifier = Modifier.padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = painterResource(id = MuGssDrawable.ic_thumb_down),
            tint = mainColor,
            contentDescription = null
        )
        Icon(
            painter = painterResource(id = MuGssDrawable.ic_pause),
            tint = mainColor,
            contentDescription = null
        )
        Icon(
            painter = painterResource(id = MuGssDrawable.ic_thumb_up),
            tint = mainColor,
            contentDescription = null
        )
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = stringResource(
                id = R.string.time,
                0,
                (progressAnimation * GameVewModel.ROUND_TIME_SECONDS).roundToInt(),
            ),
            color = mainColor,
        )
        Slider(
            modifier = Modifier
                .weight(1f)
                .height(20.dp),
            state = SliderState(progressAnimation),
            enabled = false,
            track = {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(100.dp))
                        .fillMaxWidth()
                        .height(10.dp)
                        .background(MuGssTheme.colors.gray)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth(fraction = it.value)
                            .background(mainColor)
                    )
                }
            },
            thumb = {
                Box(
                    modifier = Modifier
                        .size(18.dp)
                        .clip(CircleShape)
                        .background(mainColor)
                )
            }
        )
        Text(
            text = stringResource(
                id = R.string.time, 0,
                GameVewModel.ROUND_TIME_SECONDS
            ),
            color = mainColor,
        )
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
private fun SharedTransitionScope.TrackContent(
    track: Track,
    animatedContentScope: AnimatedContentScope,
    mainColor: Color,
    progress: Float,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AsyncImage(
            modifier = Modifier
                .padding(top = 40.dp, bottom = 20.dp)
                .sharedElement(
                    rememberSharedContentState(key = track.imageUrl + track.id),
                    animatedVisibilityScope = animatedContentScope,
                )
                .size(240.dp),
            model = track.imageUrl,
            contentDescription = null,
        )
        Text(
            modifier = Modifier
                .sharedElement(
                    rememberSharedContentState(key = track.name + track.id),
                    animatedVisibilityScope = animatedContentScope,
                )
                .skipToLookaheadSize()
                .fillMaxWidth(),
            text = track.name,
            style = MuGssTheme.typography.titleS,
            textAlign = TextAlign.Center,
            color = mainColor,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )
        Text(
            modifier = Modifier
                .sharedElement(
                    rememberSharedContentState(key = track.author + track.id),
                    animatedVisibilityScope = animatedContentScope,
                )
                .skipToLookaheadSize()
                .fillMaxWidth(),
            text = track.author,
            style = MuGssTheme.typography.bodyM,
            textAlign = TextAlign.Center,
            color = mainColor,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )
        ControlsContent(
            progress = progress,
            mainColor = mainColor
        )
    }
}

@Composable
private fun BottomContent(
    totalScore: Int,
    score: Int,
    @DrawableRes
    heartIconId: Int,
    @DrawableRes
    duckIconId: Int,
    mainColor: Color,
    onNextRound: () -> Unit,
) {
    Box(
        modifier = Modifier
            .clip(MuGssTheme.shapes.roundedCornerTop20)
            .fillMaxWidth()
            .height(196.dp)
            .background(mainColor)
            .clickable(
                onClick = onNextRound,
                indication = rememberRipple(),
                interactionSource = remember { MutableInteractionSource() }
            )
    ) {
        Row(
            Modifier.padding(start = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column(
                modifier = Modifier.padding(vertical = 30.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = score.toString(),
                    style = MuGssTheme.typography.titleM.withShadow(shadowOffset = ShadowOffset.SMALL),
                    textAlign = TextAlign.Center,
                    color = MuGssTheme.colors.white,
                )
                Text(
                    text = stringResource(id = R.string.total_score, totalScore),
                    style = MuGssTheme.typography.bodyXL.withShadow(shadowOffset = ShadowOffset.SMALL),
                    textAlign = TextAlign.Center,
                    color = MuGssTheme.colors.white,
                )
                Spacer(modifier = Modifier.weight(1f))
                IconWithShadow(iconId = MuGssDrawable.ic_arrow_forward_48)
            }
            Image(
                modifier = Modifier.padding(top = 18.dp),
                painter = painterResource(id = heartIconId),
                contentDescription = null
            )
        }
        Image(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .offset(x = 32.dp, y = 38.dp),
            painter = painterResource(id = duckIconId),
            contentDescription = null
        )
    }
}
