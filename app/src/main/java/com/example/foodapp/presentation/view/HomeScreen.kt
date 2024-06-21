package com.example.foodapp.presentation.view

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foodapp.R
import com.example.foodapp.presentation.theme.FoodAppTypography
import com.example.foodapp.presentation.theme.HomeScreenDarkRed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.sp
import com.example.foodapp.model.Area
import com.example.foodapp.presentation.theme.PlaceHolderColor
import com.example.foodapp.presentation.theme.PlaceHolderStrokeColor
import com.example.foodapp.presentation.viewmodel.MainViewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.foodapp.model.Recipe
import com.example.foodapp.presentation.navigation.BottomNavBar
import com.example.foodapp.presentation.navigation.Screen
import com.example.foodapp.presentation.theme.ScreensLightRed

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(viewModel: MainViewModel, navController: NavHostController) {

    Scaffold(bottomBar = { BottomNavBar()}) {
        val areas = viewModel.areas.value
        val recipes = viewModel.recipesByArea
        val recipeDetails = viewModel.recipeDetails.value

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(HomeScreenDarkRed)
                .padding(8.dp)
        ) {
            item {
                HomeScreenHeader()
            }

            itemsIndexed(areas) { index, area ->

                LaunchedEffect(key1 = area.strArea) {
                    viewModel.fetchRecipesByArea(area.strArea)
                }

                val recipes = recipes[area.strArea] ?: emptyList()
                HomeScreenBody(area.strArea, recipes, navController)
            }
        }
    }


}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenHeader() {
    Box(
        modifier = Modifier
            .wrapContentSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(HomeScreenDarkRed, Color.Transparent),
                    startY = 60f,
                    endY = 100f
                )
            ),
        contentAlignment = Alignment.Center
    ) {

        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            painter = painterResource(id = R.drawable.homescreenheaderimage),
            contentDescription = "Header image",
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .height(80.dp)
                .align(Alignment.TopCenter)
                .padding(24.dp)
        ) {
            OutlinedTextField(
                placeholder = {
                    Text(
                        text = "Search for recipes",
                        fontSize = 14.sp,
                        color = PlaceHolderColor
                    )
                },

                value = "",
                onValueChange = {
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(27.dp))
                    .background(Color(0xFFF5CACA))
                    .border(
                        border = BorderStroke(width = 2.dp, color = ScreensLightRed),
                        shape = RoundedCornerShape(27.dp)
                    ),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon",
                        tint = ScreensLightRed
                    )
                },
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(Color.White)
            )
        }

        Image(
            modifier = Modifier
                .size(140.dp)
                .offset(x = 7.dp),
            painter = painterResource(id = R.drawable.foodapplogo),
            alpha = 100f,
            contentDescription = "FoodApp Logo"
        )

        Text(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .offset(y = (-34).dp),
            text = "Explore culinary delights from around the globe!",
            style = FoodAppTypography.titleSmall,
            color = Color.White
        )

        // Gradient effect
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp) // Adjust height as needed
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            HomeScreenDarkRed
                        )
                    )
                )
                .align(Alignment.BottomCenter)
        )
    }
}

@Composable
fun HomeScreenBody(category: String, recipes: List<Recipe>, navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding()
    ) {
        Text(modifier = Modifier.padding(8.dp), text = category, style = FoodAppTypography.labelLarge)

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding()
        ) {
            items(recipes) { recipe ->
                RecipeCard(
                    recipe = recipe,
                    onClick = {
                        navController.navigate(Screen.Details.createRoute(recipe.idMeal))
                    }
                )
            }

        }
    }
}

@Composable
fun RecipeCard(recipe: Recipe, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .padding(end = 25.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val painter = rememberAsyncImagePainter(model = recipe.strMealThumb)

        Image(
            modifier = Modifier
                .size(200.dp)
                .clip(RoundedCornerShape(20.dp))
                .clickable(onClick = onClick),
            painter = painter,
            contentDescription = ""
        )
        Box(
            modifier = Modifier
                .width(200.dp)
                .height(30.dp), contentAlignment = Alignment.Center
        ) {
            Text(
                text = recipe.strMeal,
                style = FoodAppTypography.labelSmall,
                textAlign = TextAlign.Center,
                maxLines = 2
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun WelcomeScreenPreview() {
    HomeScreen(null!!, null!!)
}