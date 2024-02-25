package com.example.mealsapp.mealDetails.components

import android.widget.Toast
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.domain.entity.MealEntity
import com.example.mealsapp.R
import com.example.mealsapp.mealDetails.MealDetailsViewModel

@Composable
fun FavoriteButton(
    inFavorite: Boolean,
    meal: MealEntity,
    modifier: Modifier,
    mealDetailsViewModel: MealDetailsViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    if (inFavorite) {
        var isFavorite by rememberSaveable { mutableStateOf("Delete") }
        Button(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = dimensionResource(id = R.dimen.lazy_column_padding)),
            onClick = {
                if (isFavorite == "Deleted") {
                    // Show a toast if the meal is already deleted
                    Toast.makeText(context, R.string.meal_deleted, Toast.LENGTH_SHORT).show()
                } else {
                    // Delete the meal and update the state
                    mealDetailsViewModel.deleteMeal(meal)
                    isFavorite = "Deleted"
                }
            }
        ) {
            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = null,
                modifier = Modifier.size(ButtonDefaults.IconSize), tint = Color.White
            )
            Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
            Text(text = isFavorite)
        }
    } else {
        var isFavorite by rememberSaveable { mutableStateOf("") }
        isFavorite = if (mealDetailsViewModel.exist(meal.idMeal)) {
            "Added"
        } else {
            "Add To Favorite"
        }
        Button(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = dimensionResource(id = R.dimen.lazy_column_padding)),
            onClick = {
                if (isFavorite == "Added") {
                    Toast.makeText(
                        context, R.string.meal_added, Toast.LENGTH_SHORT
                    ).show()
                } else if (isFavorite == "Add To Favorite")
                    mealDetailsViewModel.insertMeal(meal)
                isFavorite = "Added"
            }

        ) {
            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = null,
                modifier = Modifier.size(ButtonDefaults.IconSize), tint = Color.White
            )
            Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
            Text(text = isFavorite)
        }
    }

}
