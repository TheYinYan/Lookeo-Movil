package com.alanturing.lookeo_movil.core.ui.theme

// import androidx.compose.material3.R
import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.alanturing.lookeo_movil.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

val PlayfairDisplayRegular = FontFamily(
    Font(R.font.playfair_display_regular)
)

val PlayfairDisplayBold = FontFamily(
    Font(R.font.playfair_display_bold)
)

val InterRegular = FontFamily(
    Font(R.font.inter_18pt_regular)
)

val InterBold = FontFamily(
    Font(R.font.inter_18pt_bold)
)