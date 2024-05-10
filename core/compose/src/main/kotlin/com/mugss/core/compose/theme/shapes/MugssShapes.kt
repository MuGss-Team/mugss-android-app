package com.mugss.core.compose.theme.shapes

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp

data class MugssShapes(
    val roundedCorner50: RoundedCornerShape = RoundedCornerShape(50.dp),
    val roundedCorner20: RoundedCornerShape = RoundedCornerShape(20.dp),
    val roundedCornerBottom40: RoundedCornerShape = RoundedCornerShape(
        bottomStart = 40.dp,
        bottomEnd = 40.dp,
    ),
    val roundedCornerTop20: RoundedCornerShape = RoundedCornerShape(
        topStart = 20.dp,
        topEnd = 20.dp,
    ),
    val roundedCorner5: RoundedCornerShape = RoundedCornerShape(5.dp),
)