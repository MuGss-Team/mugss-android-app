package com.mugss.mugss.app.unathorized.internal.widgets

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.mugss.core.compose.input.MuGssInput
import com.mugss.mugss.R

@Composable
internal fun LoginInput(
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

