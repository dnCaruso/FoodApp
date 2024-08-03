package com.example.foodapp.presentation.view

import android.widget.Space
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.foodapp.R
import com.example.foodapp.presentation.navigation.Screen
import com.example.foodapp.presentation.theme.FoodAppTypography
import com.example.foodapp.presentation.theme.ScreensLightRed
import com.example.foodapp.presentation.theme.ScreensRed
import com.example.foodapp.presentation.viewmodel.MainViewModel
import com.example.foodapp.util.UiConstants
import com.google.gson.Gson
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun RecommendedScreen(navController: NavHostController, viewModel: MainViewModel) {
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
        RecommendedScreenHeader(onClick = { navController.navigate(Screen.Home.route) })
        RecommendedScreenBody(viewModel, navController)
    }
}

@Composable
private fun RecommendedScreenHeader(onClick: () -> Unit) {
    Box() {
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(id = R.drawable.waveeffect),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier.padding(
                top = UiConstants.FLOATING_BUTTON_TOP_PADDING,
                start = UiConstants.FLOATING_BUTTON_HORIZONTAL_PADDING
            )
        ) {
            IconButton(
                modifier = Modifier
                    .size(UiConstants.FLOATING_BUTTON_SIZE)
                    .align(Alignment.TopStart)
                    .shadow(elevation = 8.dp, shape = CircleShape),
                colors = IconButtonDefaults.iconButtonColors(Color.White),
                onClick = onClick
            ) {
                Image(
                    painter = painterResource(id = R.drawable.arrowback),
                    contentDescription = null
                )
            }
        }

        Text(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(top = 50.dp),
            text = stringResource(R.string.title_recommended_screen),
            style = FoodAppTypography.headlineLarge
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun RecommendedScreenBody(viewModel: MainViewModel, navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val pagerState = rememberPagerState(pageCount = { viewModel.recommendedList.size })

        HorizontalPager(
            modifier = Modifier.fillMaxSize(),
            state = pagerState,
            key = { viewModel.recommendedList[it].idMeal }
        ) { page ->
            Box() {
                val recipeJson = Gson().toJson(viewModel.recommendedList[page])
                val encodedRecipeJson =
                    URLEncoder.encode(recipeJson, StandardCharsets.UTF_8.toString())

                Text(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .offset(y = -20.dp),
                    text = viewModel.recommendedList[page].strMeal
                )
                Image(
                    modifier = Modifier
                        .width(430.dp)
                        .height(500.dp)
                        .padding(16.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .clickable {
                            navController.navigate(
                                Screen.Details.createRoute(
                                    encodedRecipeJson
                                )
                            )
                        },
                    painter = rememberAsyncImagePainter(model = viewModel.recommendedList[page].strMealThumb),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )

                // Gradient effect
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
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
}


