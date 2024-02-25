package com.example.mealsapp.favorite

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.domain.common.FROM_FAVORITE_LIST
import com.example.mealsapp.R
import com.example.mealsapp.commonComponents.ErrorMessage
import com.example.mealsapp.commonComponents.IsLoading
import com.example.mealsapp.favorite.components.FavoriteListItem
import com.example.mealsapp.favorite.components.SwipeToDeleteContainer
import com.example.mealsapp.mealDetails.MealDetailsScreen
import com.example.mealsapp.mealDetails.MealDetailsViewModel
import com.example.mealsapp.navigation.MealsNavigationType


@Composable
fun FavoriteScreen(
    favoriteViewModel: FavoriteViewModel = hiltViewModel(),
    navController: NavHostController,
    navigationType: MealsNavigationType
) {


    val mealState by favoriteViewModel.mealList.collectAsState()
    // val activity = LocalContext.current as Activity

    val mealDetailsViewModel: MealDetailsViewModel = hiltViewModel()

    // Display loading indicator if data is loading
    if (mealState.isLoading) {
        IsLoading()
    } else {
        // Display error message if there's an error
        if (mealState.error.isNotEmpty()) {
            ErrorMessage(message = mealState.error)
        }

        // Display categories if available
        if (!mealState.meal.isNullOrEmpty()) {
            when (navigationType) {
                MealsNavigationType.BOTTOM_NAVIGATION -> {
                    LazyColumn(
                        modifier = Modifier
                            .padding(dimensionResource(id = R.dimen.lazy_column_padding))
                            .padding(
                                bottom = dimensionResource(id = R.dimen.bottom_navigation_bottom_padding)
                            ),
                        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.lazy_column_spacedBy))
                    ) {
                        items(mealState.meal!!) { meal ->
                            SwipeToDeleteContainer(item = meal,
                                onDelete = {
                                    favoriteViewModel.deleteMeal(meal)
                                }) {
                                FavoriteListItem(mealEntity = meal,
                                    onItemClick = {
                                        navController.navigate("MealDetailsScreen/${meal.idMeal}/$FROM_FAVORITE_LIST")
                                    })
                            }
                        }
                    }
                }
                MealsNavigationType.NAVIGATION_RAIL -> {
                    LazyColumn(
                        modifier = Modifier
                            .padding(dimensionResource(id = R.dimen.lazy_column_padding))
                            .padding(start = dimensionResource(id = R.dimen.navigation_rail_end_padding)),
                        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.lazy_column_spacedBy))
                    ) {
                        items(mealState.meal!!) { meal ->
                            SwipeToDeleteContainer(item = meal,
                                onDelete = {
                                    favoriteViewModel.deleteMeal(meal)
                                }) {
                                FavoriteListItem(mealEntity = meal,
                                    onItemClick = {
                                        navController.navigate("MealDetailsScreen/${meal.idMeal}/$FROM_FAVORITE_LIST")
                                    })
                            }
                        }
                    }
                }
                MealsNavigationType.PERMANENT_NAVIGATION_DRAWER -> {
                    var isClicked by rememberSaveable { mutableStateOf(false) }
                    Row {
                        LazyColumn(
                            modifier = Modifier
                                .padding(dimensionResource(id = R.dimen.lazy_column_padding))
                                .weight(0.5f)
                                .padding(start = dimensionResource(id = R.dimen.drawer_width)),
                            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.lazy_column_spacedBy))
                        ) {
                            items(mealState.meal!!) { meal ->
                                SwipeToDeleteContainer(item = meal,
                                    onDelete = {
                                        favoriteViewModel.deleteMeal(meal)
                                    }) {
                                    FavoriteListItem(mealEntity = meal,
                                        onItemClick = {
                                            mealDetailsViewModel.getMealFromFavorite(meal.idMeal)
                                            isClicked = true
                                        })
                                }
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
