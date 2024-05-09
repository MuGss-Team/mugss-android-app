package com.mugss.core.compose.buttons

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mugss.core.compose.theme.MuGssTheme

@Composable
fun MugssTextButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        modifier = modifier.height(50.dp),
        onClick = onClick,
        shape = MuGssTheme.shapes.roundedCorner50,
        colors = ButtonDefaults.buttonColors(
            containerColor = MuGssTheme.colors.primary,
            contentColor = MuGssTheme.colors.white,
        )
    ) {
        Text(
            text = text,
            style = MuGssTheme.typography.bodyM
        )
    }
}

@Composable
@Preview
private fun ButtonPreview() {
    MuGssTheme {
        MugssTextButton(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            text = "Войти",
            onClick = { /*TODO*/ }
        )
    }
}