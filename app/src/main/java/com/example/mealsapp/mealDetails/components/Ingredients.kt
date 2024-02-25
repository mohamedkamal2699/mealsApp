package com.example.mealsapp.mealDetails.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import com.example.domain.entity.MealEntity
import com.example.mealsapp.R

data class IngredientsItem(val ingredients: String, val measure: String)

@Composable
fun Ingredients(meal: MealEntity) {


    val ingredients = listOf(
        IngredientsItem(meal.strIngredient1, meal.strMeasure1),
        IngredientsItem(meal.strIngredient2, meal.strMeasure2),
        IngredientsItem(meal.strIngredient3, meal.strMeasure3),
        IngredientsItem(meal.strIngredient4, meal.strMeasure4),
        IngredientsItem(meal.strIngredient5, meal.strMeasure5),
        IngredientsItem(meal.strIngredient6, meal.strMeasure6),
        IngredientsItem(meal.strIngredient7, meal.strMeasure7),
        IngredientsItem(meal.strIngredient8, meal.strMeasure8),
        IngredientsItem(meal.strIngredient9, meal.strMeasure9),
        IngredientsItem(meal.strIngredient10, meal.strMeasure10),
        IngredientsItem(meal.strIngredient11, meal.strMeasure11),
        IngredientsItem(meal.strIngredient12, meal.strMeasure12),
        IngredientsItem(meal.strIngredient13, meal.strMeasure13),
        IngredientsItem(meal.strIngredient14, meal.strMeasure14),
        IngredientsItem(meal.strIngredient15, meal.strMeasure15),
        IngredientsItem(meal.strIngredient16, meal.strMeasure16),
        IngredientsItem(meal.strIngredient17, meal.strMeasure17),
        IngredientsItem(meal.strIngredient18, meal.strMeasure18),
        IngredientsItem(meal.strIngredient19, meal.strMeasure19),
        IngredientsItem(meal.strIngredient20, meal.strMeasure20),
    )

    Column {
        Divider()
        Row(Modifier.fillMaxWidth()) {
            TableCell(
                text = stringResource(id = R.string.ingredients),
                style = MaterialTheme.typography.titleMedium
            )
            TableCell(
                text = stringResource(id = R.string.measures),
                style = MaterialTheme.typography.titleMedium
            )
        }
        Divider()
        Row(Modifier.fillMaxWidth()) {
            Column {
                for (value in ingredients) {
                    if (value.ingredients.isNotEmpty()
                        && value.ingredients != " "
                        && value.ingredients != "null"
                        && value.measure.isNotEmpty()
                        && value.measure != " "
                        && value.measure != "null"
                    ) {
                        Row {
                            TableCell(
                                text = value.ingredients,
                                style = MaterialTheme.typography.bodyLarge
                            )
                            TableCell(
                                text = value.measure,
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                        Divider()
                    }

                }
            }
        }
    }
}

@Composable
fun RowScope.TableCell(
    text: String,
    style: TextStyle
) {
    Text(
        text = text,
        modifier = Modifier
            .weight(5f)
            .padding(dimensionResource(id = R.dimen.text_tag_padding)),
        style = style
    )
}
