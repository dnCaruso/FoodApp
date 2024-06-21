package com.example.foodapp.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foodapp.R
import com.example.foodapp.presentation.theme.FoodAppTypography
import com.example.foodapp.presentation.theme.ScreensLightRed
import com.example.foodapp.presentation.theme.ScreensRed
import com.example.foodapp.util.UiConstants

@Composable
fun RecommendedScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    listOf(ScreensRed, ScreensLightRed),
                    startY = 500f,
                    endY = 200f
                )
            )
    ) {
        RecommendedScreenHeader()
        RecommendedScreamBody()
    }
}

@Composable
private fun RecommendedScreenHeader(modifier: Modifier = Modifier) {
    Box() {
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(id = R.drawable.waveeffect),
            contentDescription = "",
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier.padding(
                top = UiConstants.FLOATING_BUTTON_TOP_PADDING,
                start = UiConstants.FLOATING_BUTTON_HORIZONTAL_PADDING
            )
        ) {
            IconButton(modifier = Modifier
                .size(UiConstants.FLOATING_BUTTON_SIZE)
                .align(Alignment.TopStart)
                .shadow(elevation = 8.dp, shape = CircleShape),
                colors = IconButtonDefaults.iconButtonColors(Color.White),
                onClick = { /*TODO*/ }) {

                Image(
                    painter = painterResource(id = R.drawable.arrowback),
                    contentDescription = "Go Back Icon"
                )
            }
        }

        Text(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(top = 50.dp),
            text = "Recommended",
            style = FoodAppTypography.headlineLarge
        )
    }
}

@Composable
private fun RecommendedScreamBody(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Big Mac", style = FoodAppTypography.displayMedium)
        Box() {
            Image(
                modifier = Modifier
                    .width(430.dp)
                    .height(500.dp)
                    .padding(16.dp)
                    .clip(RoundedCornerShape(20.dp)),
                painter = painterResource(id = R.drawable.recommendedfood),
                contentDescription = "",
                contentScale = ContentScale.Crop
            )

            // Gradient effect
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                ScreensRed
                            )
                        )
                    )
                    .align(Alignment.BottomCenter)
            )
        }
    }
}

@Preview
@Composable
private fun RecommendedScreenPreview() {
    RecommendedScreen()
}