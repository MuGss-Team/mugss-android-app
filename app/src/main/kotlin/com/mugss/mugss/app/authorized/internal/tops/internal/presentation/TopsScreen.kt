package com.mugss.mugss.app.authorized.internal.tops.internal.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.mugss.core.compose.cards.MuGssCard
import com.mugss.core.compose.icons.IconBack
import com.mugss.core.compose.theme.MuGssTheme
import com.mugss.core.compose.theme.typo.shadow.ShadowOffset
import com.mugss.core.compose.theme.typo.shadow.withShadow
import com.mugss.mugss.R
import com.mugss.mugss.app.authorized.internal.tops.internal.presentation.contract.TopsState
import com.mugss.mugss.app.authorized.internal.tops.internal.presentation.stateholder.TopsViewModel

@Composable
internal fun TopsScreen(navController: NavController, viewModel: TopsViewModel = hiltViewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MuGssTheme.colors.primary)
            .safeDrawingPadding(),
    ) {
        IconBack(
            modifier = Modifier
                .align(Alignment.Start)
                .padding(top = 10.dp, start = 16.dp, bottom = 6.dp),
            onClick = { navController.popBackStack() }
        )
        Text(
            modifier = Modifier
                .padding(bottom = 24.dp)
                .align(Alignment.CenterHorizontally),
            text = stringResource(R.string.tops),
            textAlign = TextAlign.Center,
            style = MuGssTheme.typography.titleXL.withShadow(ShadowOffset.BIG),
            color = MuGssTheme.colors.white
        )
        val state by viewModel.state.collectAsStateWithLifecycle(TopsState.Loading)
        val selectedMode = remember { mutableIntStateOf(0) }
        when (state) {
            TopsState.Loading -> {
                viewModel.getModes()
            }
            is TopsState.Content -> {
                val content = state as TopsState.Content
                if (content.positions == null) {
                    viewModel.getTopByMode(content.modesTitle[selectedMode.intValue])
                } else {
                    LazyRow(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally),
                        horizontalArrangement = Arrangement.spacedBy(20.dp)
                    ) {
                        itemsIndexed(content.modesTitle) { index, modeTitle ->
                            MuGssCard(
                                modifier = Modifier
                                    .padding(horizontal = 4.dp)
                                    .clickable {
                                        selectedMode.intValue = index
                                        viewModel.getTopByMode(content.modesTitle[index])
                                    }
                            ) {
                                Text(
                                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp),
                                    text = modeTitle,
                                    style = MuGssTheme.typography.bodyXL.withShadow(ShadowOffset.SMALL),
                                    color = MuGssTheme.colors.white
                                )
                            }
                        }
                    }
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 24.dp, vertical = 30.dp)
                            .align(Alignment.CenterHorizontally)
                            .shadow(
                                elevation = 10.dp,
                                shape = MuGssTheme.shapes.roundedCorner20,
                            )
                            .background(
                                color = MuGssTheme.colors.backgroundComponents,
                                shape = MuGssTheme.shapes.roundedCorner20,
                            ),
                    ) {
                        itemsIndexed(content.positions) { index, position ->
                            TopPositionText(
                                modifier = Modifier
                                    .padding(horizontal = 24.dp, vertical = 16.dp)
                                    .fillMaxWidth(),
                                position = index + 1,
                                login = position.login ?: "noname",
                                score = position.score.toString()
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
internal fun TopPositionText(modifier: Modifier, position: Int, login: String, score: String) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Text(
            modifier = Modifier.weight(0.1f),
            text = position.toString(),
            textAlign = TextAlign.Start,
            maxLines = 1,
            style = MuGssTheme.typography.bodyXXL.withShadow(ShadowOffset.BIG),
            color = MuGssTheme.colors.white
        )
        Text(
            modifier = Modifier.weight(0.5f),
            text = login,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Start,
            maxLines = 1,
            style = MuGssTheme.typography.bodyXL.withShadow(ShadowOffset.SMALL),
            color = MuGssTheme.colors.white
        )
        Text(
            modifier = Modifier.weight(0.4f),
            text = score,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.End,
            maxLines = 1,
            style = MuGssTheme.typography.bodyXL.withShadow(ShadowOffset.SMALL),
            color = MuGssTheme.colors.white
        )
    }
}