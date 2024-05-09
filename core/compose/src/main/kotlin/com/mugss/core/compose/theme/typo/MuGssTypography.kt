package com.mugss.core.compose.theme.typo

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.mugss.core.compose.R

internal val LocalMuGssTypography = staticCompositionLocalOf<MuGssTypography> {
    error("No typo provided")
}

private val fontFamily = FontFamily(listOf(Font(R.font.pixy)))

data class MuGssTypography(
    val titleXXl: TextStyle,
    val titleXL: TextStyle,
    val titleL: TextStyle,
    val titleM: TextStyle,
    val titleS: TextStyle,
    val bodyXXL: TextStyle,
    val bodyXL: TextStyle,
    val bodyL: TextStyle,
    val bodyM: TextStyle,
    val bodyS: TextStyle,
)

fun typo() = MuGssTypography(
    titleXXl = TextStyle(
        fontFamily = fontFamily,
        fontSize = 90.sp,
        lineHeight = 104.sp,
        fontWeight = FontWeight.W400,
    ),
    titleXL = TextStyle(
        fontFamily = fontFamily,
        fontSize = 64.sp,
        lineHeight = 50.sp,
        fontWeight = FontWeight.W400,
    ),
    titleL = TextStyle(
        fontFamily = fontFamily,
        fontSize = 55.sp,
        lineHeight = 50.sp,
        fontWeight = FontWeight.W400,
    ),
    titleM = TextStyle(
        fontFamily = fontFamily,
        fontSize = 50.sp,
        lineHeight = 50.sp,
        fontWeight = FontWeight.W400,
    ),
    titleS = TextStyle(
        fontFamily = fontFamily,
        fontSize = 32.sp,
        lineHeight = 28.sp,
        fontWeight = FontWeight.W400,
    ),
    bodyXXL = TextStyle(
        fontFamily = fontFamily,
        fontSize = 30.sp,
        lineHeight = 50.sp,
        fontWeight = FontWeight.W400,
    ),
    bodyXL = TextStyle(
        fontFamily = fontFamily,
        fontSize = 28.sp,
        lineHeight = 30.sp,
        fontWeight = FontWeight.W400,
    ),
    bodyL = TextStyle(
        fontFamily = fontFamily,
        fontSize = 25.sp,
        lineHeight = 30.sp,
        fontWeight = FontWeight.W400,
    ),
    bodyM = TextStyle(
        fontFamily = fontFamily,
        fontSize = 20.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.W400,
    ),
    bodyS = TextStyle(
        fontFamily = fontFamily,
        fontSize = 15.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight.W400,
    ),
)