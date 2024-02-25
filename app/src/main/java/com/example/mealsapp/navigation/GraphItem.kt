package com.example.mealsapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val icon: ImageVector, val label: String) {
    object Home : BottomNavItem("home", Icons.Default.Home, "Home")
    object Area : BottomNavItem("area", Icons.Default.LocationOn, "Area")
    object Search : BottomNavItem("search", Icons.Default.Search, "Search")
    object Favorite : BottomNavItem("favorite", Icons.Default.Favorite, "Favorite")
}

sealed class Screen(val route: String) {
    object MealListScreen : Screen(route = "MealListScreen")
    object MealDetailsScreen : Screen(route = "MealDetailsScreen")


}
