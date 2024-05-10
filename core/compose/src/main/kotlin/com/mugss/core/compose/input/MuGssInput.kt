package com.mugss.core.compose.input

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mugss.core.compose.modifier.optionalClickable
import com.mugss.core.compose.theme.MuGssTheme

@Composable
fun MuGssInput(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    cursorBrush: Color = MuGssTheme.colors.primary,
    @DrawableRes
    trailingIconId: Int? = null,
    onTrailingIconClick: (() -> Unit)? = null,
    label: String? = null,
) {
    Column(modifier) {
        label?.let {
            Text(
                text = label,
                style = MuGssTheme.typography.bodyM,
                color = MuGssTheme.colors.gray,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
        BasicTextField(
            modifier = Modifier
                .padding(start = 16.dp)
                .clip(MuGssTheme.shapes.roundedCorner50)
                .background(MuGssTheme.colors.white)
                .height(38.dp)
                .border(
                    width = 3.dp,
                    color = MuGssTheme.colors.primary,
                    shape = MuGssTheme.shapes.roundedCorner50,
                ),
            value = value,
            onValueChange = onValueChange,
            singleLine = true,
            enabled = enabled,
            keyboardOptions = keyboardOptions,
            visualTransformation = visualTransformation,
            cursorBrush = SolidColor(cursorBrush),
            textStyle = MuGssTheme.typography.bodyM.copy(color = MuGssTheme.colors.primary),
        ) { innerTextField ->
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Box(modifier = Modifier.weight(1f, false)) {
                    innerTextField()
                }
                trailingIconId?.let {
                    Icon(
                        modifier = Modifier
                            .size(24.dp)
                            .optionalClickable(onTrailingIconClick),
                        painter = painterResource(id = trailingIconId),
                        contentDescription = null,
                        tint = MuGssTheme.colors.primary,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun InputPreview() {
    MuGssTheme {
        var text by remember {
            mutableStateOf("")
        }
        MuGssInput(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            value = text,
            onValueChange = { text = it },
            label = "логин:"
        )
    }
}