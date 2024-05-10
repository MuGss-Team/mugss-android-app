package com.mugss.mugss.app.unathorized.internal.authorization.internal.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mugss.core.compose.buttons.MugssTextButton
import com.mugss.core.compose.checkbox.CheckboxWithText
import com.mugss.core.compose.theme.MuGssTheme
import com.mugss.core.compose.theme.colors.radialGradientPrimaryBackground
import com.mugss.mugss.R
import com.mugss.mugss.app.unathorized.internal.authorization.internal.presentation.stateholder.AuthorizationViewModel
import com.mugss.mugss.app.unathorized.internal.registration.api.navigation.Registration
import com.mugss.mugss.app.unathorized.internal.widgets.LoginInput
import com.mugss.mugss.app.unathorized.internal.widgets.PasswordInput
import com.mugss.mugss.app.unathorized.internal.widgets.UnauthorizedWidget

@Composable
internal fun AuthorizationScreen(
    navController: NavController,
    authorizationViewModel: AuthorizationViewModel = hiltViewModel(),
) {
    val state = authorizationViewModel.state
    val inputModifier = Modifier
        .padding(start = 24.dp, end = 40.dp, bottom = 18.dp)
        .fillMaxWidth()
    NavigationEventsHandler(
        authorizationViewModel = authorizationViewModel,
        navController = navController
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .radialGradientPrimaryBackground()
            .safeDrawingPadding()
            .verticalScroll(rememberScrollState()),
        contentAlignment = Alignment.Center,
    ) {
        UnauthorizedWidget(title = stringResource(id = R.string.welcome)) {
            LoginInput(
                modifier = inputModifier,
                value = state.login,
                onValueChange = authorizationViewModel::onLoginChange,
            )
            PasswordInput(
                modifier = inputModifier,
                value = state.password,
                onValueChange = authorizationViewModel::onPasswordChange
            )

            RememberMeCheckbox(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 24.dp,
                        end = 40.dp,
                        bottom = 32.dp
                    ),
                checked = state.rememberMe,
                onCheckedChange = authorizationViewModel::onCheckboxClicked
            )
            MugssTextButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp)
                    .padding(bottom = 18.dp),
                text = stringResource(id = R.string.enter),
                onClick = authorizationViewModel::onAuthorizeButtonClick,
                enabled = state.password.isNotBlank() && state.login.isNotBlank()
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 18.dp)
                    .clickable(
                        indication = null,
                        interactionSource = remember {
                            MutableInteractionSource()
                        }
                    ) {
                        navController.navigate(Registration)
                    },
                text = stringResource(id = R.string.trigger_to_register),
                style = MuGssTheme.typography.bodyM,
                color = MuGssTheme.colors.gray,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
private fun RememberMeCheckbox(
    modifier: Modifier,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
) {
    CheckboxWithText(
        modifier = modifier,
        checked = checked,
        onCheckedChange = onCheckedChange,
        text = stringResource(id = R.string.remember_me)
    )
}