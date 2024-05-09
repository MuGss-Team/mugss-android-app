package com.mugss.mugss.app.registration

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.mugss.core.compose.MuGssDrawable
import com.mugss.core.compose.input.MuGssInput

@Composable
fun SecretInput(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
) {
    var passwordVisible by remember {
        mutableStateOf(false)
    }

    MuGssInput(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        label = label,
        trailingIconId = if (passwordVisible) {
            MuGssDrawable.visibility_off_24
        } else {
            MuGssDrawable.visibility_24
        },
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        onTrailingIconClick = {
            passwordVisible = !passwordVisible
        }
    )
}