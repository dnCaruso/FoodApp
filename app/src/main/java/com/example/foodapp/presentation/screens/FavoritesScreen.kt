package com.example.foodapp.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.foodapp.R
import com.example.foodapp.model.Recipe
import com.example.foodapp.presentation.navigation.Screen
import com.example.foodapp.presentation.theme.FoodAppTypography
import com.example.foodapp.presentation.theme.ScreensLightRed
import com.example.foodapp.presentation.theme.ScreensRed
import com.example.foodapp.presentation.viewmodel.FavoritesViewModel
import com.example.foodapp.util.UiConstants
import com.google.gson.Gson
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun FavoritesScreen(navController: NavHostController, viewModel: FavoritesViewModel) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    listOf(
                        ScreensRed, ScreensLightRed
                    )
                )
            )
    ) {
        item {
            favoriteScreenHeader(
                onClick = { navController.navigate(Screen.Home.route) },
                viewModel = viewModel
            )
        }
        item {
            favoriteScreenBody(
                viewModel.favorites.collectAsState().value,
                viewModel = viewModel,
                navController = navController
            )
        }
    }
}

@Composable
private fun favoriteScreenHeader(onClick: () -> Unit, viewModel: FavoritesViewModel) {
    Box() {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            painter = painterResource(id = R.drawable.favoritescreenheader),
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

        // Gradient effect
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
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

@Composable
private fun favoriteScreenBody(
    recipes: List<Recipe>,
    viewModel: FavoritesViewModel,
    navController: NavHostController
) {

    Column(
        Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(R.string.first_title_favorite_screen),
            style = FoodAppTypography.headlineMedium
        )
        Text(
            modifier = Modifier.padding(bottom = 16.dp),
            text = stringResource(R.string.second_title_favorite_screen),
            style = FoodAppTypography.headlineMedium
        )
        favoriteRecipesRow(recipes = recipes, viewModel = viewModel, navController = navController)
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun favoriteRecipesRow(
    recipes: List<Recipe>,
    viewModel: FavoritesViewModel,
    navController: NavHostController
) {
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        maxItemsInEachRow = 2
    ) {
        recipes.forEach { recipe ->
            recipeCard(
                recipe = recipe,
                onClickDelete = { viewModel.removeFavorite(recipe) },
                navController = navController
            )
        }
    }
}

@Composable
private fun recipeCard(
    recipe: Recipe,
    onClickDelete: () -> Unit,
    navController: NavHostController
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box() {
            val recipeJson = Gson().toJson(recipe)
            val encodedRecipeJson = URLEncoder.encode(recipeJson, StandardCharsets.UTF_8.toString())

            Image(
                modifier = Modifier
                    .size(150.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .clickable { navController.navigate(Screen.Details.createRoute(encodedRecipeJson)) },
                painter = rememberAsyncImagePainter(model = recipe.strMealThumb),
                contentDescription = null
            )

            IconButton(
                modifier = Modifier
                    .size(35.dp)
                    .align(Alignment.TopEnd)
                    .offset(x = 10.dp, y = -10.dp),
                colors = IconButtonDefaults.iconButtonColors(
                    ScreensRed
                ),
                onClick = onClickDelete
            ) {
                Image(
                    modifier = Modifier.size(20.dp),
                    painter = painterResource(id = R.drawable.deleteicon),
                    contentDescription = null
                )
            }
        }
        Text(
            modifier = Modifier
                .padding(start = 6.dp, top = 4.dp)
                .size(100.dp),
            text = recipe.strMeal,
            style = FoodAppTypography.labelSmall,
            textAlign = TextAlign.Center
        )
    }
}