package com.mugss.mugss.app.authorized.internal.game.internal.presentation.game_result

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mugss.core.compose.MuGssDrawable
import com.mugss.core.compose.cards.MuGssCard
import com.mugss.core.compose.theme.MuGssTheme
import com.mugss.core.compose.theme.typo.shadow.ShadowOffset
import com.mugss.core.compose.theme.typo.shadow.withShadow
import com.mugss.mugss.R

@Composable
fun GameResultScreen(
    scores: Int,
    countOfGuessed: Int,
    countOfRounds: Int,
    onHomeNavigate: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MuGssTheme.colors.primary)
            .safeDrawingPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            modifier = Modifier
                .padding(vertical = 60.dp)
                .fillMaxWidth(),
            text = stringResource(id = R.string.yours_scores),
            style = MuGssTheme.typography.titleL.withShadow(shadowOffset = ShadowOffset.BIG),
            textAlign = TextAlign.Center,
            color = MuGssTheme.colors.white,
        )
        MuGssCard(modifier = Modifier.padding(horizontal = 24.dp)) {
            Column(
                modifier = Modifier
                    .padding(start = 20.dp)
                    .padding(vertical = 60.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.scores_gained),
                    style = MuGssTheme.typography.bodyXXL.withShadow(shadowOffset = ShadowOffset.SMALL),
                    color = MuGssTheme.colors.white,
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = scores.toString(),
                    style = MuGssTheme.typography.titleM.withShadow(shadowOffset = ShadowOffset.SMALL),
                    color = MuGssTheme.colors.white,
                    textAlign = TextAlign.Center,
                )
                Text(
                    text = stringResource(id = R.string.songs_guessed),
                    style = MuGssTheme.typography.bodyXXL.withShadow(shadowOffset = ShadowOffset.SMALL),
                    color = MuGssTheme.colors.white,
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "$countOfGuessed/$countOfRounds",
                    style = MuGssTheme.typography.titleM.withShadow(shadowOffset = ShadowOffset.SMALL),
                    color = MuGssTheme.colors.white,
                    textAlign = TextAlign.Center,
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        MuGssCard(
            Modifier
                .width(116.dp)
                .height(66.dp),
            background = MuGssTheme.colors.white,
            onClick = onHomeNavigate,
        ) {
            Icon(
                modifier = Modifier.align(Alignment.Center),
                painter = painterResource(id = MuGssDrawable.ic_home_48),
                contentDescription = null,
                tint = MuGssTheme.colors.primary,
            )
        }
    }
}