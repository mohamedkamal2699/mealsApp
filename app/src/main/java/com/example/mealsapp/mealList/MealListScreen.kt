package com.example.mealsapp.mealList


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.domain.common.FROM_Meal_LIST
import com.example.mealsapp.R
import com.example.mealsapp.commonComponents.ErrorMessage
import com.example.mealsapp.commonComponents.IsLoading
import com.example.mealsapp.commonComponents.MealListLI
import com.example.mealsapp.commonComponents.TopBar
import com.example.mealsapp.mealDetails.MealDetailsScreen
import com.example.mealsapp.mealDetails.MealDetailsViewModel
import com.example.mealsapp.navigation.MealsNavigationType

@Composable
fun ListOfMeals(
    mealListViewModel: MealListViewModel = hiltViewModel(),
    navController: NavHostController,
    navigationType: MealsNavigationType
) {

    val mealListState by mealListViewModel.mealList.collectAsState()

    val mealViewModel: MealDetailsViewModel = hiltViewModel()

    // Display loading indicator if data is loading
    if (mealListState.isLoading) {
        IsLoading()
    } else {
        // Display error message if there's an error
        if (mealListState.error.isNotEmpty()) {
            ErrorMessage(message = mealListState.error)
        }

        // Display list of meals if available
        if (!mealListState.mealList.isNullOrEmpty()) {

            when (navigationType) {
                MealsNavigationType.BOTTOM_NAVIGATION, MealsNavigationType.NAVIGATION_RAIL -> {
                    Column(modifier = Modifier.fillMaxSize()) {
                        TopBar(
                            onBackButtonClicked = { navController.navigateUp() },
                            title = stringResource(id = R.string.meals_list),
                            modifier = Modifier
                                .padding(
                                    end = dimensionResource(id = R.dimen.lazy_column_padding),
                                    start = dimensionResource(id = R.dimen.lazy_column_padding),
                                    top = dimensionResource(id = R.dimen.top_bar_top_padding)
                                )
                                .clip(shape = MaterialTheme.shapes.large)
                        )
                        LazyColumn(
                            modifier = Modifier
                                .padding(dimensionResource(id = R.dimen.lazy_column_padding))
                                .padding(),
                            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.lazy_column_spacedBy))
                        ) {
                            items(mealListState.mealList!!) { meal ->
                                MealListLI(mealList = meal,
                                    onItemClick = {
                                        navController.navigate("MealDetailsScreen/${meal.idMeal}/$FROM_Meal_LIST")
                                    })
                            }
                        }
                    }
                }
                MealsNavigationType.PERMANENT_NAVIGATION_DRAWER -> {
                    var isClicked by rememberSaveable { mutableStateOf(false) }
                    Row(
                        modifier = Modifier
                            .padding()
                            .padding(start = dimensionResource(id = R.dimen.drawer_width))
                    ) {
                        LazyColumn(
                            modifier = Modifier
                                .padding(dimensionResource(id = R.dimen.lazy_column_padding))
                                .weight(0.5f),
                            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.lazy_column_spacedBy))
                        ) {
                            items(mealListState.mealList!!) { meal ->
                                MealListLI(mealList = meal,
                                    onItemClick = {
                                        mealViewModel.getMealFromRemote(meal.idMeal)
                                        isClicked = true
                                    })
                            }
                        }
                        Column(
                            modifier = Modifier
                                .padding(dimensionResource(id = R.dimen.lazy_column_padding))
                                .weight(0.5f)
                        ) {
                            if (!isClicked) {
                                Box(Modifier.fillMaxSize()) {
                                    Text(
                                        modifier = Modifier.align(Alignment.Center),
                                        text = stringResource(id = R.string.chose_meal)
                                    )
                                }
                            } else
                                MealDetailsScreen(
                                    navigationType = navigationType,
                                    navController = navController, isFullPage = false
                                )
                        }
                    }
                }
            }

        }
    }
}