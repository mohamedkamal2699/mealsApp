package com.example.mealsapp.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.domain.common.FILTER_CATEGORY
import com.example.mealsapp.R
import com.example.mealsapp.commonComponents.ErrorMessage
import com.example.mealsapp.commonComponents.IsLoading
import com.example.mealsapp.navigation.MealsNavigationType

@Composable
fun HomeScreen(
    categoryViewModel: HomeViewModel = hiltViewModel(),
    navController: NavHostController,
    navigationType: MealsNavigationType
) {

    val categoryState by categoryViewModel.categories.collectAsState()

    // Display loading indicator if data is loading
    if (categoryState.isLoading) {
        IsLoading()
    } else {
        // Display error message if there's an error
        if (categoryState.error.isNotEmpty()) {
            ErrorMessage(message = categoryState.error)
        }

        // Display categories if available
        if (!categoryState.category.isNullOrEmpty()) {

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
                        items(categoryState.category!!) { category ->
                            HomeListItem(category,
                                onItemClick =
                                {
                                    navController.navigate("MealListScreen/${category.strCategory}/$FILTER_CATEGORY")
                                })
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
                        items(categoryState.category!!) { category ->
                            HomeListItem(category,
                                onItemClick =
                                {
                                    navController.navigate("MealListScreen/${category.strCategory}/$FILTER_CATEGORY")
                                })
                        }
                    }
                }
                MealsNavigationType.PERMANENT_NAVIGATION_DRAWER -> {
                    LazyColumn(
                        modifier = Modifier
                            .padding(dimensionResource(id = R.dimen.lazy_column_padding))
                            .padding(start = dimensionResource(id = R.dimen.drawer_width)),
                        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.lazy_column_spacedBy))
                    ) {
                        items(categoryState.category!!) { category ->
                            HomeListItem(category,
                                onItemClick =
                                {
                                    navController.navigate("MealListScreen/${category.strCategory}/$FILTER_CATEGORY")
                                })
                        }
                    }
                }
            }
        }
    }
}
