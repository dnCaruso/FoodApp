package com.example.foodapp.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.foodapp.R

val RedHatDisplay = FontFamily(
    Font(R.font.redhatdisplayregular, FontWeight.Normal),
    Font(R.font.redhatdisplaymedium, FontWeight.Medium),
    Font(R.font.redhatdisplaysemibold, FontWeight.SemiBold),
    Font(R.font.redhatdisplaybold, FontWeight.Bold),
    Font(R.font.redhatdisplayblack, FontWeight.Black)
)


// Set of Material typography styles to start with
val FoodAppTypography = Typography(
    bodySmall = TextStyle(
        fontFamily = RedHatDisplay,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        color = Color.White
    ),
    bodyLarge = TextStyle(
        fontFamily = RedHatDisplay,
        fontWeight = FontWeight.Black,
        fontSize = 20.sp,
        color = Color.White
    ),
    labelSmall = TextStyle(
        fontFamily = RedHatDisplay,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        color = Color.White
    ),
    labelMedium = TextStyle(
        fontFamily = RedHatDisplay,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = Color.Black
    ),
    labelLarge = TextStyle(
        fontFamily = RedHatDisplay,
        fontWeight = FontWeight.Black,
        fontSize = 22.sp,
        color = Color.White
    ),
    titleSmall = TextStyle(
        fontFamily = RedHatDisplay,
        fontWeight = FontWeight.Black,
        fontSize = 15.sp,
        color = Color.White
    ),
    titleMedium = TextStyle(
        fontFamily = RedHatDisplay,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        color = ScreensLightRed
    ),
    titleLarge = TextStyle(
        fontFamily = RedHatDisplay,
        fontWeight = FontWeight.Medium,
        fontSize = 24.sp,
        color = Color.White
    ),
    headlineMedium = TextStyle(
        fontFamily = RedHatDisplay,
        fontWeight = FontWeight.Black,
        fontSize = 32.sp,
        color = Color.White,
        letterSpacing = 6.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = RedHatDisplay,
        fontWeight = FontWeight.Black,
        fontSize = 36.sp,
        color = Color.White,
        letterSpacing = 6.sp
    ),
    displayMedium = TextStyle(
        fontFamily = RedHatDisplay,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
        color = Color.White,
        letterSpacing = 13.sp
    )
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
