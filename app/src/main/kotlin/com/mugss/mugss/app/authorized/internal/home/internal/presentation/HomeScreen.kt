package com.mugss.mugss.app.authorized.internal.home.internal.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mugss.core.compose.MuGssDrawable
import com.mugss.core.compose.theme.MuGssTheme
import com.mugss.core.compose.theme.typo.shadow.ShadowOffset
import com.mugss.core.compose.theme.typo.shadow.withShadow
import com.mugss.mugss.R

@Composable
fun HomeScreen(
    navigateToModeSelection: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MuGssTheme.colors.primary)
            .safeDrawingPadding()
            .pointerInput(navigateToModeSelection) {
                detectTapGestures { navigateToModeSelection() }
            }
    ) {
        Text(
            modifier = Modifier
                .padding(top = 40.dp)
                .padding(horizontal = 54.dp),
            text = stringResource(id = R.string.guess_the_song),
            style = MuGssTheme.typography.titleXL.withShadow(shadowOffset = ShadowOffset.BIG),
            color = MuGssTheme.colors.white,
            textAlign = TextAlign.End,
        )
        Spacer(modifier = Modifier.weight(1f))
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(id = MuGssDrawable.main_duck),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 90.dp)
                .padding(horizontal = 32.dp),
            text = stringResource(id = R.string.touch_to_play),
            style = MuGssTheme.typography.bodyL.withShadow(shadowOffset = ShadowOffset.SMALL),
            color = MuGssTheme.colors.white,
            textAlign = TextAlign.Center,
        )
    }
}