package com.example.mealsapp.search

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.domain.common.FROM_Meal_LIST
import com.example.mealsapp.R
import com.example.mealsapp.commonComponents.ErrorMessage
import com.example.mealsapp.commonComponents.IsLoading
import com.example.mealsapp.commonComponents.MealListLI
import com.example.mealsapp.navigation.MealsNavigationType

@Composable
fun Search(
    navController: NavHostController,
    searchViewModel: SearchViewModel = hiltViewModel(),
    navigationType: MealsNavigationType
) {

    // Collect meal list state as a State
    val mealListState by searchViewModel.mealList.collectAsState()

    // Row layout containing the search bar
    when (navigationType) {
        MealsNavigationType.BOTTOM_NAVIGATION -> {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                SearchBar()
            }
        }
        MealsNavigationType.NAVIGATION_RAIL -> {
            Row(
                modifier = Modifier
                    .padding()
                    .padding(start = dimensionResource(id = R.dimen.navigation_rail_end_padding))
                    .fillMaxWidth()
            ) {
                SearchBar()
            }
        }
        MealsNavigationType.PERMANENT_NAVIGATION_DRAWER -> {
            Row(
                modifier = Modifier
                    .padding()
                    .padding(start = dimensionResource(id = R.dimen.drawer_width))
                    .fillMaxWidth()
            ) {
                SearchBar()
            }
        }
    }

    // Display loading indicator if data is loading
    if (mealListState.isLoading) {
        IsLoading()
    } else {
        // Display error message if there's an error
        if (mealListState.error.isNotEmpty()) {
            ErrorMessage(message = mealListState.error)
        }

        // Display meal list if available
        if (!mealListState.search.isNullOrEmpty()) {
            when (navigationType) {
                MealsNavigationType.BOTTOM_NAVIGATION -> {
                    LazyColumn(
                        modifier = Modifier
                            .padding(dimensionResource(id = R.dimen.lazy_column_padding))
                            .padding(
                                top = dimensionResource(id = R.dimen.search_lazy_column_top_padding),
                                bottom = dimensionResource(id = R.dimen.bottom_navigation_bottom_padding)
                            ),
                        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.lazy_column_spacedBy))
                    ) {
                        items(mealListState.search!!) { meal ->
                            MealListLI(mealList = meal,
                                onItemClick = {
                                    navController.navigate("MealDetailsScreen/${meal.idMeal}/$FROM_Meal_LIST")
                                })
                        }
                    }
                }
                MealsNavigationType.NAVIGATION_RAIL -> {
                    LazyColumn(
                        modifier = Modifier
                            .padding(dimensionResource(id = R.dimen.lazy_column_padding))
                            .padding(
                                top = dimensionResource(id = R.dimen.search_lazy_column_top_padding),
                                start = dimensionResource(id = R.dimen.navigation_rail_end_padding)
                            ),
                        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.lazy_column_spacedBy))
                    ) {
                        items(mealListState.search!!) { meal ->

                            MealListLI(mealList = meal,
                                onItemClick = {
                                    navController.navigate("MealDetailsScreen/${meal.idMeal}/$FROM_Meal_LIST")
                                })
                        }
                    }
                }
                MealsNavigationType.PERMANENT_NAVIGATION_DRAWER -> {
                    LazyColumn(
                        modifier = Modifier
                            .padding(dimensionResource(id = R.dimen.lazy_column_padding))
                            .padding(
                                top = dimensionResource(id = R.dimen.search_lazy_column_top_padding),
                                start = dimensionResource(id = R.dimen.drawer_width)
                            ),
                        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.lazy_column_spacedBy))
                    ) {
                        items(mealListState.search!!) { meal ->
                            MealListLI(mealList = meal,
                                onItemClick = {
                                    navController.navigate("MealDetailsScreen/${meal.idMeal}/$FROM_Meal_LIST")
                                })
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SearchBar(searchViewModel: SearchViewModel = hiltViewModel()) {

    // Access the local focus manager
    val localFocusManger = LocalFocusManager.current

    // State to hold the text input value
    var textState by rememberSaveable { mutableStateOf("") }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = dimensionResource(id = R.dimen.search_bar_heightIn))
            .padding(
                vertical = dimensionResource(id = R.dimen.search_bar_padding_vertical),
                horizontal = dimensionResource(id = R.dimen.search_bar_padding_horizontal)
            ),
        shape = CircleShape,
        value = textState,
        onValueChange = {
            textState = it
            // Trigger search when text changes
            searchViewModel.searchMeal(textState)
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedContainerColor = MaterialTheme.colorScheme.surface,
        ),
        placeholder = {
            Text(stringResource(id = R.string.search))
        },

        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions { localFocusManger.clearFocus() })
}

