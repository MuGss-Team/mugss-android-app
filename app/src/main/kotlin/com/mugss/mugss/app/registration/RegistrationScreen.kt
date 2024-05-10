package com.mugss.mugss.app.registration

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mugss.core.compose.buttons.MugssTextButton
import com.mugss.core.compose.input.MuGssInput
import com.mugss.core.compose.theme.colors.radialGradientPrimaryBackground
import com.mugss.mugss.R
import com.mugss.mugss.app.registration.stateholder.RegistrationViewModel

@Composable
fun RegistrationScreen(
    registrationViewModel: RegistrationViewModel = hiltViewModel(),
) {
    val state = registrationViewModel.state
    val inputModifier = Modifier
        .padding(start = 24.dp, end = 40.dp, bottom = 18.dp)
        .fillMaxWidth()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .radialGradientPrimaryBackground()
            .safeDrawingPadding()
            .verticalScroll(rememberScrollState()),
        contentAlignment = Alignment.Center,
    ) {
        RegistrationWidget(title = stringResource(id = R.string.welcome)) {
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
                onClick = registrationViewModel::onRegisterClick
            )
        }
    }
}

@Composable
private fun LoginInput(
    modifier: Modifier,
    value: String,
    onValueChange: (String) -> Unit,
) {
    MuGssInput(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        label = stringResource(id = R.string.login)
    )
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
private fun PasswordInput(
    modifier: Modifier,
    value: String,
    onValueChange: (String) -> Unit,
) {
    SecretInput(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        label = stringResource(id = R.string.password)
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
