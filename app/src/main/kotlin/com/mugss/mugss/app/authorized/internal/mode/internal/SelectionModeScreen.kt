package com.mugss.mugss.app.authorized.internal.mode.internal

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mugss.core.compose.MuGssDrawable
import com.mugss.core.compose.icons.IconBack
import com.mugss.core.compose.loading.LoadingScreen
import com.mugss.core.compose.theme.MuGssTheme
import com.mugss.core.compose.theme.typo.shadow.ShadowOffset
import com.mugss.core.compose.theme.typo.shadow.withShadow
import com.mugss.mugss.R
import com.mugss.mugss.app.authorized.internal.mode.internal.presentation.contract.ModePresentation
import com.mugss.mugss.app.authorized.internal.mode.internal.presentation.contract.SelectionModeState
import com.mugss.mugss.app.authorized.internal.mode.internal.presentation.stateholder.SelectionModeViewModel
import kotlinx.collections.immutable.ImmutableList

@Composable
internal fun SelectionModeScreen(
    navController: NavController,
    selectionModeViewModel: SelectionModeViewModel = hiltViewModel(),
) {
    LaunchedEffect(key1 = Unit) {
        selectionModeViewModel.fetchModes()
    }
    Crossfade(
        targetState = selectionModeViewModel.state,
        label = "selectionModeScreenCrossfade",
    ) { state ->
        when (state) {
            is SelectionModeState.Loading -> LoadingScreen()
            is SelectionModeState.Content -> Content(
                onBack = navController::popBackStack,
                modes = state.modes
            )
        }
    }
}

@Composable
private fun Content(
    onBack: () -> Unit,
    modes: ImmutableList<ModePresentation>,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MuGssTheme.colors.primary)
            .safeDrawingPadding()
    ) {
        IconBack(
            modifier = Modifier.padding(top = 10.dp, start = 16.dp, bottom = 4.dp),
            onClick = onBack,
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 50.dp),
            text = stringResource(id = R.string.chose_mode),
            textAlign = TextAlign.Center,
            style = MuGssTheme.typography.titleL.withShadow(shadowOffset = ShadowOffset.BIG),
            color = MuGssTheme.colors.white,
        )
        LazyColumn(
            contentPadding = PaddingValues(
                top = 44.dp,
                bottom = 30.dp,
            ),
            verticalArrangement = Arrangement.spacedBy(22.dp),
        ) {
            items(modes) {
                ModeCard(mode = it)
            }
            item {
                MuGssCard(
                    horizontalArrangement = Arrangement.SpaceAround,
                ) {
                    Icon(
                        modifier = Modifier.size(96.dp),
                        painter = painterResource(id = MuGssDrawable.ic_plus_96),
                        contentDescription = null,
                        tint = MuGssTheme.colors.white,
                    )
                }
            }
        }
    }
}

@Composable
private fun ModeCard(
    mode: ModePresentation,
) {
    Box(
        modifier = Modifier.height(IntrinsicSize.Min)
    ) {
        val scrollState = rememberScrollState()
        MuGssCard(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .padding(
                    top = 6.dp,
                    bottom = 10.dp
                )
        ) {
            Box(
                modifier = Modifier
                    .padding(end = 16.dp)
                    .size(104.dp)
                    .clip(CircleShape)
                    .background(MuGssTheme.colors.white),
                contentAlignment = Alignment.Center,
            ) {
                Image(
                    painter = painterResource(id = mode.icon),
                    contentDescription = null
                )
            }
            Column {
                Text(
                    text = mode.title,
                    style = MuGssTheme.typography.bodyXL.withShadow(shadowOffset = ShadowOffset.SMALL),
                    color = MuGssTheme.colors.white,
                    textAlign = TextAlign.Center,
                )
                Text(
                    modifier = Modifier.verticalScroll(scrollState),
                    text = mode.description,
                    style = MuGssTheme.typography.bodyS,
                    color = MuGssTheme.colors.white,
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .fillMaxHeight()
                .clip(MuGssTheme.shapes.roundedCorner20),
            contentAlignment = Alignment.BottomEnd,
        ) {
            AnimatedVisibility(visible = scrollState.canScrollForward) {
                Spacer(
                    modifier = Modifier
                        .height(30.dp)
                        .fillMaxWidth()
                        .background(
                            Brush.linearGradient(
                                listOf(
                                    MuGssTheme.colors.backgroundComponents.copy(alpha = 0.4f),
                                    MuGssTheme.colors.backgroundComponents,
                                )
                            )
                        )
                )
            }
        }
    }
}

@Composable
fun MuGssCard(
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.SpaceBetween,
    content: @Composable RowScope.() -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .height(166.dp)
            .shadow(
                elevation = 10.dp,
                shape = MuGssTheme.shapes.roundedCorner20,
            )
            .background(
                color = MuGssTheme.colors.backgroundComponents,
                shape = MuGssTheme.shapes.roundedCorner20,
            )
            .then(modifier)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = horizontalArrangement,
            content = content,
        )
    }
}