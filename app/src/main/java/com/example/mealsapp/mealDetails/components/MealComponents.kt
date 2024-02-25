package com.example.mealsapp.mealDetails.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import coil.compose.AsyncImage
import com.example.mealsapp.R
import com.example.mealsapp.mealDetails.MealState

@Composable
fun MealComponents(
    modifier: Modifier,
    mealState: MealState
) {
    val meal = mealState.meal!!
    Box(modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .verticalScroll(rememberScrollState())
        ) {
            AsyncImage(
                model = meal.strMealThumb, contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(dimensionResource(id = R.dimen.lazy_column_padding))
                    .padding(bottom = dimensionResource(id = R.dimen.meal_components_bottom_padding))
            ) {

                Text(
                    text = meal.strMeal,
                    modifier = Modifier.padding(dimensionResource(id = R.dimen.text_padding)),
                    style = MaterialTheme.typography.headlineMedium,
                    maxLines = 1
                )
                Text(
                    text = meal.strCategory,
                    modifier = Modifier.padding(dimensionResource(id = R.dimen.text_padding)),
                    style = MaterialTheme.typography.headlineSmall
                )
                Text(
                    text = meal.strArea,
                    modifier = Modifier.padding(dimensionResource(id = R.dimen.text_padding)),
                    style = MaterialTheme.typography.headlineSmall
                )

                Tags(str = meal.strTags.toString())
                Text(
                    text = meal.strInstructions,
                    modifier = Modifier.padding(dimensionResource(id = R.dimen.text_padding)),
                    style = MaterialTheme.typography.bodyLarge
                )

                Ingredients(meal = meal)
            }
        }
        FavoriteButton(
            modifier = Modifier.align(Alignment.BottomEnd),
            inFavorite = mealState.inFavorite!!, meal = meal
        )
    }
}
