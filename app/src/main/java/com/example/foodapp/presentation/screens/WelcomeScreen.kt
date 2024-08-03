package com.example.foodapp.presentation.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.foodapp.R
import com.example.foodapp.presentation.navigation.Screen
import com.example.foodapp.presentation.theme.FoodAppTypography
import com.example.foodapp.presentation.theme.HomeScreenRed

@Composable
fun WelcomeScreen(navController: NavHostController?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.Black, HomeScreenRed
                    ),
                    startY = 2000f,
                    endY = 100f
                )
            )
        ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        WelcomeScreenBody(navController)
    }
}

@Composable
private fun WelcomeScreenBody(navController: NavHostController?) {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceEvenly) {
        Text(
            modifier = Modifier.padding(top = 120.dp),
            text = "Welcome to \nFoodApp",
            style = FoodAppTypography.headlineMedium,
            textAlign = TextAlign.Center
        )

        Box(modifier = Modifier
            .wrapContentSize()
            .offset(y = 100.dp), contentAlignment = Alignment.BottomCenter) {
            Image(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .size(350.dp)
                    .offset(),
                painter = painterResource(R.drawable.cat),
                contentDescription = ""
            )
        }

        Box(modifier = Modifier.wrapContentSize(), contentAlignment = Alignment.BottomCenter) {
            Image(
                modifier = Modifier
                    .fillMaxSize(),
                painter = painterResource(id = R.drawable.curvedtoplayout),
                contentDescription = "",
                contentScale = ContentScale.FillBounds
            )

            ElevatedButton(modifier = Modifier
                .width(250.dp)
                .align(Alignment.TopCenter)
                .offset(y = 30.dp),
                colors = ButtonDefaults.elevatedButtonColors(HomeScreenRed),
                elevation = ButtonDefaults.elevatedButtonElevation(8.dp, pressedElevation = 10.dp),
                onClick = { navController?.navigate(Screen.Home.route) }) {
                Text(text = "Start", style = FoodAppTypography.bodyLarge)
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomStart)
                    .padding(30.dp),
                horizontalArrangement = Arrangement.Absolute.SpaceBetween
            ) {
                welcomeScreenIcon(iconImage = R.drawable.forkandknifeicon, "Recipes")
                welcomeScreenIcon(iconImage = R.drawable.hearticon, "Save your \n favorite recipes")
            }

        }
    }

}

@Composable
fun welcomeScreenIcon(iconImage: Int, iconLabel: String) {
    Column(
        modifier = Modifier.wrapContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .border(2.dp, Color.LightGray, shape = CircleShape)
                .padding(16.dp),
            color = Color.Transparent
        ) {
            Image(painter = painterResource(id = iconImage), contentDescription = "")
        }
        Text(text = iconLabel, style = FoodAppTypography.labelMedium, textAlign = TextAlign.Center)
    }
}

@Preview
@Composable
private fun WelcomeScreenPreview() {
    WelcomeScreen(navController = null)
}