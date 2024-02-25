package com.example.mealsapp.mealDetails


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mealsapp.R
import com.example.mealsapp.commonComponents.ErrorMessage
import com.example.mealsapp.commonComponents.IsLoading
import com.example.mealsapp.commonComponents.TopBar
import com.example.mealsapp.mealDetails.components.MealComponents
import com.example.mealsapp.navigation.MealsNavigationType

@Composable
fun MealDetailsScreen(
    mealViewModel: MealDetailsViewModel = hiltViewModel(),
    navigationType: MealsNavigationType,
    navController: NavController,
    isFullPage: Boolean
) {

    val mealState by mealViewModel.meal.collectAsState()

    // Display loading indicator if data is loading
    if (mealState.isLoading) {
        IsLoading()
    } else {
        // Display error message if there's an error
        if (mealState.error.isNotEmpty()) {
            ErrorMessage(message = mealState.error)
        }

        // Display meal data if available
        if (mealState.meal != null) {
            // Display the meal details
            when (navigationType) {
                MealsNavigationType.BOTTOM_NAVIGATION, MealsNavigationType.NAVIGATION_RAIL -> {
                    Column(modifier = Modifier.fillMaxSize()) {
                        TopBar(
                            onBackButtonClicked = { navController.navigateUp() },
                            title = stringResource(id = R.string.meal_details),
                            modifier = Modifier
                        )
                        MealComponents(modifier = Modifier, mealState)
                    }
                }
                MealsNavigationType.PERMANENT_NAVIGATION_DRAWER -> {
                    if (isFullPage) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(dimensionResource(id = R.dimen.lazy_column_padding))
                                .padding(start = dimensionResource(id = R.dimen.drawer_width))
                        ) {
                            MealComponents(modifier = Modifier, mealState)
                        }
                    } else
                        Column(modifier = Modifier.fillMaxSize()) {
                            MealComponents(modifier = Modifier, mealState)
                        }
                }
            }
        }
    }
}
