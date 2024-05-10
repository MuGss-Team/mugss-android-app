package com.mugss.mugss.app.unathorized.internal.widgets

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mugss.core.compose.theme.MuGssTheme

@Composable
internal fun UnauthorizedWidget(
    modifier: Modifier = Modifier,
    title: String,
    content: @Composable ColumnScope.() -> Unit,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 38.dp),
        colors = CardDefaults.cardColors(
            containerColor = MuGssTheme.colors.white,
        ),
        shape = MuGssTheme.shapes.roundedCorner50,

        ) {
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 10.dp),
            text = title,
            color = MuGssTheme.colors.gray,
            style = MuGssTheme.typography.titleL,
            textAlign = TextAlign.Center,
        )
        content()
    }
}