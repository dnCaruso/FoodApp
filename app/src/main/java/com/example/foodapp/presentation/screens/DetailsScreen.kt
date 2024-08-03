package com.example.foodapp.presentation.view

import android.widget.Toast
import androidx.compose.foundation.Canvas
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.foodapp.R
import com.example.foodapp.model.Recipe
import com.example.foodapp.presentation.navigation.Screen
import com.example.foodapp.presentation.theme.FoodAppTypography
import com.example.foodapp.presentation.theme.RecipeTitleRed
import com.example.foodapp.presentation.theme.ScreensLightRed
import com.example.foodapp.presentation.theme.ScreensRed
import com.example.foodapp.presentation.theme.SectionTitleRed
import com.example.foodapp.presentation.viewmodel.FavoritesViewModel
import com.example.foodapp.presentation.viewmodel.MainViewModel
import com.example.foodapp.util.UiConstants

@Composable
fun DetailsScreen(
    recipe: Recipe,
    viewModel: MainViewModel,
    favoriteViewModel: FavoritesViewModel,
    navController: NavHostController
) {
    viewModel.fetchMealDetails(recipe.idMeal)

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        ScreensLightRed, ScreensRed
                    )
                )
            )
    ) {
        item {
            detailsScreenHeader(
                recipe = recipe,
                onClickReturn = { navController.navigate(Screen.Home.route) },
                viewModel = favoriteViewModel
            )
        }

        item {
            detailsScreenBody(
                ingredients = viewModel.getIngredients(),
                measures = viewModel.getMeasures(),
                strInstructions = viewModel.getInstructions()
            )
        }
    }
}

@Composable
fun detailsScreenHeader(recipe: Recipe, onClickReturn: () -> Unit, viewModel: FavoritesViewModel) {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .wrapContentSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(ScreensRed, Color.Transparent),
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
            painter = rememberAsyncImagePainter(model = recipe.strMealThumb),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = UiConstants.FLOATING_BUTTON_TOP_PADDING,
                    start = UiConstants.FLOATING_BUTTON_HORIZONTAL_PADDING,
                    end = UiConstants.FLOATING_BUTTON_HORIZONTAL_PADDING
                )
                .align(Alignment.TopStart)
        ) {
            IconButton(
                modifier = Modifier
                    .size(UiConstants.FLOATING_BUTTON_SIZE)
                    .align(Alignment.TopStart)
                    .shadow(elevation = 8.dp, shape = CircleShape),
                colors = IconButtonDefaults.iconButtonColors(Color.White),
                onClick = onClickReturn
            ) {
                Image(
                    painter = painterResource(id = R.drawable.arrowback),
                    contentDescription = null
                )
            }

            IconButton(
                modifier = Modifier
                    .size(UiConstants.FLOATING_BUTTON_SIZE)
                    .align(Alignment.TopEnd)
                    .shadow(elevation = 8.dp, shape = CircleShape)
                    .padding(),
                colors = IconButtonDefaults.iconButtonColors(Color.White),
                onClick = {
                    if (viewModel.addFavorite(recipe)) {
                        Toast.makeText(
                            context,
                            context.getString(R.string.toast_added), Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            context,
                            context.getString(R.string.toast_not_added), Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.favoriteicon),
                    contentDescription = null
                )
            }
        }

        // Gradient effect
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
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
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(bottomStart = 28.dp, bottomEnd = 28.dp)
            )
            .clip(
                RoundedCornerShape(bottomStart = 28.dp, bottomEnd = 28.dp)
            )
            .background(RecipeTitleRed),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = recipe.strMeal,
            style = FoodAppTypography.titleLarge,
            textAlign = TextAlign.Center
        )
    }
    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
fun detailsScreenBody(
    ingredients: List<String?>?,
    measures: List<String?>?,
    strInstructions: String
) {
    Box() {
        Row(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(8.dp)
                .height(37.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(SectionTitleRed),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                modifier = Modifier.padding(),
                text = stringResource(R.string.label_ingredients),
                style = FoodAppTypography.titleMedium
            )
        }
        Image(
            modifier = Modifier.size(46.dp),
            painter = painterResource(id = R.drawable.ingredientsicon),
            contentDescription = null
        )
    }

    showIngredientsSection(
        ingredients = ingredients,
        measures = measures
    )

    Spacer(modifier = Modifier.height(12.dp))

    Box() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .height(37.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(SectionTitleRed),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                modifier = Modifier.padding(),
                text = stringResource(R.string.label_instructions),
                style = FoodAppTypography.titleMedium
            )
        }
        Image(
            modifier = Modifier
                .size(50.dp)
                .offset(-10.dp),
            painter = painterResource(id = R.drawable.instructionsicon),
            contentDescription = null
        )
    }
    Text(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
        text = strInstructions,
        style = FoodAppTypography.bodySmall,
        textAlign = TextAlign.Start
    )
}

@Composable
private fun showIngredientsSection(
    ingredients: List<String?>?,
    measures: List<String?>?
) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {

        ingredients?.forEachIndexed { index, ingredient ->
            ingredientItem(ingredient = ingredient, measures?.getOrNull(index) ?: "")
        }
    }
}

@Composable
private fun ingredientItem(ingredient: String?, measure: String?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        Canvas(modifier = Modifier.size(10.dp)) {
            drawCircle(color = ScreensRed)
        }
        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = stringResource(R.string.ingredient, ingredient!!),
            style = FoodAppTypography.bodySmall,
            textAlign = TextAlign.Start
        )
        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = measure!!,
            style = FoodAppTypography.bodySmall,
            textAlign = TextAlign.Start
        )
    }
}
