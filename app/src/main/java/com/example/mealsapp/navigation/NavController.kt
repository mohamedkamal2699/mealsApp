package com.example.mealsapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mealsapp.area.AreaScreen
import com.example.mealsapp.favorite.FavoriteScreen
import com.example.mealsapp.home.HomeScreen
import com.example.mealsapp.mealDetails.MealDetailsScreen
import com.example.mealsapp.mealList.ListOfMeals
import com.example.mealsapp.search.Search

@Composable
fun NavigationHost(
    navController: NavHostController,
    navigationType: MealsNavigationType
) {

    NavHost(navController, startDestination = BottomNavItem.Home.route) {

        composable(BottomNavItem.Home.route) {
            HomeScreen(navController = navController, navigationType = navigationType)
        }

        composable(route = Screen.MealListScreen.route + "/{MEAL_LIST_NAME}/{FILTER_CHAR}") {
            ListOfMeals(navController = navController, navigationType = navigationType)
        }

        composable(route = BottomNavItem.Search.route) {
            Search(navController = navController, navigationType = navigationType)
        }

        composable(route = Screen.MealDetailsScreen.route + "/{MEAL_ID}/{IS_FROM_FAVORITE}") {
            MealDetailsScreen(
                navigationType = navigationType, navController = navController,
                isFullPage = true
            )
        }

        composable(BottomNavItem.Favorite.route) {
            FavoriteScreen(navController = navController, navigationType = navigationType)
        }


        composable(BottomNavItem.Area.route) {
            AreaScreen(navController = navController, navigationType = navigationType)
        }
    }
}




