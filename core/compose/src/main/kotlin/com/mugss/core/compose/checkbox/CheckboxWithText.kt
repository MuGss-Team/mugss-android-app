package com.mugss.core.compose.checkbox

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mugss.core.compose.theme.MuGssTheme

@Composable
fun CheckboxWithText(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    text: String? = null,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val color by animateColorAsState(
            targetValue = if (checked) {
                MuGssTheme.colors.primary
            } else {
                MuGssTheme.colors.white
            },
            label = "checkboxColor"
        )
        Box(
            modifier = Modifier
                .clip(MuGssTheme.shapes.roundedCorner5)
                .size(25.dp)
                .clickable(
                    indication = rememberRipple(
                        bounded = false,
                        radius = 24.dp
                    ),
                    interactionSource = remember {
                        MutableInteractionSource()
                    },
                    onClick = { onCheckedChange(!checked) }
                )
                .background(color)
                .border(
                    width = 3.dp,
                    color = MuGssTheme.colors.primary,
                    shape = MuGssTheme.shapes.roundedCorner5,
                )
        )
        text?.let {
            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = text,
                style = MuGssTheme.typography.bodyM,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = MuGssTheme.colors.gray,
            )
        }
    }
}

@Preview
@Composable
private fun PreviewCheckbox() {
    MuGssTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MuGssTheme.colors.white),
            contentAlignment = Alignment.Center
        ) {
            var checked by remember {
                mutableStateOf(false)
            }
            CheckboxWithText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                checked = checked,
                onCheckedChange = { checked = it },
                text = "Запомнить меня"
            )
        }
    }
}