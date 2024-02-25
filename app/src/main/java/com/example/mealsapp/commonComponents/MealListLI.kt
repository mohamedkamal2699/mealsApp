package com.example.mealsapp.commonComponents

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import coil.compose.AsyncImage
import com.example.domain.entity.MealList
import com.example.mealsapp.R

@Composable
fun MealListLI(
    mealList: MealList,
    onItemClick: (MealList) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable { onItemClick(mealList) },
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            AsyncImage(
                model = mealList.strMealThumb,
                contentDescription = "",
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.list_meals_image_size))
            )

            Text(
                text = mealList.strMeal,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.text_padding)),
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}