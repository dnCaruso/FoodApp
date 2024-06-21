package com.example.foodapp.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foodapp.R
import com.example.foodapp.presentation.theme.FoodAppTypography
import com.example.foodapp.presentation.theme.ScreensLightRed
import com.example.foodapp.presentation.theme.ScreensRed
import com.example.foodapp.util.UiConstants

@Composable
fun FavoritesScreen(modifier: Modifier = Modifier) {
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
        item { favoriteScreenHeader() }
        item { favoriteScreenBody() }
    }
}

@Composable
private fun favoriteScreenHeader(modifier: Modifier = Modifier) {
    Box() {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(),
            painter = painterResource(id = R.drawable.favoritescreenheader),
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
                .shadow(elevation = 8.dp, shape = CircleShape),
                colors = IconButtonDefaults.iconButtonColors(Color.White),
                onClick = { /*TODO*/ }) {

                Image(
                    painter = painterResource(id = R.drawable.arrowback),
                    contentDescription = "Go Back Icon"
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
private fun favoriteScreenBody() {
    Column(
        Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Favorite", style = FoodAppTypography.headlineMedium)
        Text(
            modifier = Modifier.padding(bottom = 16.dp),
            text = "Recipes",
            style = FoodAppTypography.headlineMedium
        )
        favoriteRecipesRow()
        favoriteRecipesRow()
        favoriteRecipesRow()
        favoriteRecipesRow()
        favoriteRecipesRow()
    }
}

@Composable
private fun favoriteRecipesRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        recipeCard(0, "Spaghetti alla Carbonara")
        Spacer(modifier = Modifier.width(24.dp))
        recipeCard(0, "Spaghetti alla Carbonara")
    }
}

@Composable
private fun recipeCard(recipeImage: Int, recipeTitle: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box() {
            Image(
                modifier = Modifier
                    .size(150.dp)
                    .clip(RoundedCornerShape(20.dp)),
                painter = painterResource(recipeImage),
                contentDescription = "Recipe image"
            )

            IconButton(modifier = Modifier
                .size(35.dp)
                .align(Alignment.TopEnd)
                .offset(x = 10.dp, y = -10.dp),
                colors = IconButtonDefaults.iconButtonColors(
                    ScreensRed
                ),
                onClick = { /*TODO*/ }) {
                Image(
                    modifier = Modifier.size(20.dp),
                    painter = painterResource(id = R.drawable.deleteicon),
                    contentDescription = "Favorite Icon"
                )
            }
        }
        Text(
            modifier = Modifier.padding(start = 6.dp, top = 4.dp),
            text = recipeTitle,
            style = FoodAppTypography.labelSmall,
            textAlign = TextAlign.Center
        )
    }
}


@Preview
@Composable
private fun FavoritesScreenPreview() {
    FavoritesScreen()
}