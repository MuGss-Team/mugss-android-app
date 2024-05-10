package com.mugss.mugss.app.unathorized.internal.registration.internal.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mugss.core.compose.MuGssDrawable
import com.mugss.core.compose.buttons.MugssTextButton
import com.mugss.core.compose.icons.IconWithShadow
import com.mugss.core.compose.input.MuGssInput
import com.mugss.core.compose.theme.colors.radialGradientPrimaryBackground
import com.mugss.mugss.R
import com.mugss.mugss.app.unathorized.internal.registration.internal.presentation.stateholder.RegistrationViewModel
import com.mugss.mugss.app.unathorized.internal.widgets.LoginInput
import com.mugss.mugss.app.unathorized.internal.widgets.PasswordInput
import com.mugss.mugss.app.unathorized.internal.widgets.SecretInput
import com.mugss.mugss.app.unathorized.internal.widgets.UnauthorizedWidget

@Composable
internal fun RegistrationScreen(
    navController: NavController,
    registrationViewModel: RegistrationViewModel = hiltViewModel(),
) {
    val state = registrationViewModel.state
    val inputModifier = Modifier
        .padding(start = 24.dp, end = 40.dp, bottom = 18.dp)
        .fillMaxWidth()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .radialGradientPrimaryBackground()
            .safeDrawingPadding()
            .verticalScroll(rememberScrollState()),
    ) {
        IconWithShadow(
            modifier = Modifier
                .padding(
                    start = 16.dp,
                    top = 10.dp
                )
                .size(48.dp)
                .clickable(
                    onClick = navController::popBackStack,
                    indication = rememberRipple(
                        radius = 24.dp,
                        bounded = false,
                    ),
                    interactionSource = remember {
                        MutableInteractionSource()
                    }
                ),
            iconId = MuGssDrawable.ic_arrow_48
        )
        Spacer(modifier = Modifier.weight(1f))
        UnauthorizedWidget(
            title = stringResource(id = R.string.create_account)
        ) {
            LoginInput(
                modifier = inputModifier,
                value = state.login,
                onValueChange = registrationViewModel::onLoginChange,
            )
            EmailInput(
                modifier = inputModifier,
                value = state.email,
                onValueChange = registrationViewModel::onEmailChange,
            )
            PasswordInput(
                modifier = inputModifier,
                value = state.password,
                onValueChange = registrationViewModel::onPasswordChange
            )
            PasswordConfirmationInput(
                modifier = inputModifier,
                value = state.confirmationPassword,
                onValueChange = registrationViewModel::onPasswordConfirmationChange
            )
            MugssTextButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp)
                    .padding(top = 22.dp, bottom = 36.dp),
                text = stringResource(id = R.string.registrate),
                onClick = registrationViewModel::onRegisterClick,
                enabled = state.password == state.confirmationPassword &&
                        state.password.isNotBlank() &&
                        state.confirmationPassword.isNotBlank()
                        && state.email.isNotBlank()
                        && state.login.isNotBlank()
            )
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
private fun EmailInput(
    modifier: Modifier,
    value: String,
    onValueChange: (String) -> Unit,
) {
    MuGssInput(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        label = stringResource(id = R.string.email)
    )
}

@Composable
private fun PasswordConfirmationInput(
    modifier: Modifier,
    value: String,
    onValueChange: (String) -> Unit,
) {
    SecretInput(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        label = stringResource(id = R.string.password_confirmation)
    )
}
