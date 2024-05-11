package com.mugss.core.compose.loading

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mugss.core.compose.MuGssDrawable
import com.mugss.core.compose.R
import com.mugss.core.compose.theme.MuGssTheme

@Composable
fun LoadingScreen() {
    val transition = rememberInfiniteTransition(
        label = "alphaLoadingScreenImageTransition"
    )
    val alpha by transition.animateFloat(
        initialValue = 1f,
        targetValue = 0.1f,
        animationSpec = loadingAnimationSpec(),
        label = "alphaAnimation"
    )
    val size by transition.animateFloat(
        initialValue = 1f,
        targetValue = 0.95f,
        animationSpec = loadingAnimationSpec(),
        label = "sizeAnimation"
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MuGssTheme.colors.primary)
            .safeDrawingPadding()
            .padding(horizontal = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.weight(0.22f))
        Box(
            modifier = Modifier.size(264.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier
                    .padding(bottom = 50.dp)
                    .size(264.dp * size)
                    .alpha(alpha),
                painter = painterResource(id = MuGssDrawable.ic_duck_264),
                contentDescription = null
            )
        }
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.loading_description),
            style = MuGssTheme.typography.bodyM,
            color = MuGssTheme.colors.white,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.weight(0.32f))
    }
}

@Composable
private fun loadingAnimationSpec() = infiniteRepeatable<Float>(
    animation = tween(
        durationMillis = 700,
        easing = FastOutSlowInEasing
    ),
    repeatMode = RepeatMode.Reverse,
)

@Preview
@Composable
private fun LoadingPreview() {
    MuGssTheme {
        LoadingScreen()
    }
}