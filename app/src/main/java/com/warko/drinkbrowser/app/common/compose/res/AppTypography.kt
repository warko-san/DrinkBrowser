package com.warko.drinkbrowser.app.common.compose.res

import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.sp
import com.warko.drinkbrowser.R

object AppTypography {

    private val ManropeTextStyle = TextStyle(
        fontFamily = FontFamily(
            Font(R.font.manrope_regular),
            Font(R.font.manrope_medium, FontWeight.Medium),
            Font(R.font.manrope_bold, FontWeight.Bold)
        ),
        color = Palette.Grey,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false
        ),
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.None
        )
    )

    //======================= DRINK BROWSER STYLES =======================//

    val DisplayLgBold = ManropeTextStyle.copy(
        fontSize = 32.sp,
        lineHeight = 52.sp,
        fontWeight = FontWeight.W700
    )

    val DisplayMdBold = ManropeTextStyle.copy(
        fontSize = 28.sp,
        lineHeight = 42.sp,
        fontWeight = FontWeight.W700
    )

    val DisplaySmBold = ManropeTextStyle.copy(
        fontSize = 24.sp,
        lineHeight = 38.sp,
        fontWeight = FontWeight.W700
    )

    val DisplayXsBold = ManropeTextStyle.copy(
        fontSize = 20.sp,
        lineHeight = 32.sp,
        fontWeight = FontWeight.W700
    )

    val DisplayXxlBold = ManropeTextStyle.copy(
        fontSize = 40.sp,
        lineHeight = 62.sp,
        fontWeight = FontWeight.W700
    )

    val TextMdMedium = ManropeTextStyle.copy(
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.W500
    )

    val TextMdBold = ManropeTextStyle.copy(
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.W700
    )

    val TextSmMedium = ManropeTextStyle.copy(
        fontSize = 14.sp,
        lineHeight = 22.sp,
        fontWeight = FontWeight.W500
    )

    val TextSmBold = ManropeTextStyle.copy(
        fontSize = 14.sp,
        lineHeight = 22.sp,
        fontWeight = FontWeight.W700
    )

    val TextXsMedium = ManropeTextStyle.copy(
        fontSize = 12.sp,
        lineHeight = 16.sp,
        fontWeight = FontWeight.W500
    )

    val TextXsBold = ManropeTextStyle.copy(
        fontSize = 12.sp,
        lineHeight = 16.sp,
        fontWeight = FontWeight.W700
    )

    val TextLgMedium = ManropeTextStyle.copy(
        fontSize = 18.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.W500
    )

    val TextLgBold = ManropeTextStyle.copy(
        fontSize = 18.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.W700
    )

    val Button = TextMdBold.copy(
        color = Palette.White
    )

    val Button_Disabled = Button.copy(color = Palette.GreyDisabled)
}