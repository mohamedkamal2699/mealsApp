package com.example.mealsapp.area


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.domain.common.FILTER_AREA
import com.example.domain.entity.Area
import com.example.mealsapp.R
import com.example.mealsapp.commonComponents.ErrorMessage
import com.example.mealsapp.commonComponents.IsLoading
import com.example.mealsapp.navigation.MealsNavigationType

@Composable
fun AreaScreen(
    navController: NavHostController,
    areaViewModel: AreaViewModel = hiltViewModel(),
    navigationType: MealsNavigationType
) {

    val areaState by areaViewModel.area.collectAsState()

    // Display loading indicator if data is loading
    if (areaState.isLoading) {
        IsLoading()
    } else {
        // Display error message if there's an error
        if (areaState.error.isNotEmpty()) {
            ErrorMessage(message = areaState.error)
        }

        // Display categories if available
        if (!areaState.area.isNullOrEmpty()) {
            when (navigationType) {
                MealsNavigationType.BOTTOM_NAVIGATION -> {
                    LazyColumn(
                        modifier = Modifier
                            .padding(dimensionResource(id = R.dimen.lazy_column_padding))
                            .padding(
                                bottom = dimensionResource(id = R.dimen.bottom_navigation_bottom_padding)
                            )
                    ) {
                        items(areaState.area!!) { area ->
                            AreaListItem(area = area,
                                onItemClick = {
                                    navController.navigate("MealListScreen/${area.strArea}/$FILTER_AREA")
                                })
                        }
                    }
                }
                MealsNavigationType.NAVIGATION_RAIL -> {
                    LazyColumn(
                        modifier = Modifier
                            .padding(dimensionResource(id = R.dimen.lazy_column_padding))
                            .padding(start = dimensionResource(id = R.dimen.navigation_rail_end_padding))
                    ) {
                        items(areaState.area!!) { area ->
                            AreaListItem(area = area,
                                onItemClick = {
                                    navController.navigate("MealListScreen/${area.strArea}/$FILTER_AREA")
                                })
                        }
                    }
                }
                MealsNavigationType.PERMANENT_NAVIGATION_DRAWER -> {
                    LazyColumn(
                        modifier = Modifier
                            .padding(dimensionResource(id = R.dimen.lazy_column_padding))
                            .padding(start = dimensionResource(id = R.dimen.drawer_width))
                    ) {
                        items(areaState.area!!) { area ->
                            AreaListItem(area = area,
                                onItemClick = {
                                    navController.navigate("MealListScreen/${area.strArea}/$FILTER_AREA")
                                })
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AreaListItem(
    area: Area,
    onItemClick: (Area) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = dimensionResource(id = R.dimen.card_padding_vertical))
            .clickable {
                onItemClick(area)
            }, shape = MaterialTheme.shapes.medium
    ) {
        Text(
            text = area.strArea,
            modifier = Modifier.padding(dimensionResource(id = R.dimen.text_area_padding)),
            style = MaterialTheme.typography.headlineMedium
        )
    }
}