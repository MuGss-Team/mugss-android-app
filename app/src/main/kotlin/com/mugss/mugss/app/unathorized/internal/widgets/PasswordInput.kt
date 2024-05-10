package com.mugss.mugss.app.unathorized.internal.widgets

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.mugss.mugss.R

@Composable
internal fun PasswordInput(
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