package com.example.mealsapp.navigation

import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController


@Composable
fun MealsApp(
    navController: NavHostController = rememberNavController(),
    windowSize: WindowWidthSizeClass
) {

    val navigationType: MealsNavigationType = when (windowSize) {
        WindowWidthSizeClass.Compact -> {
            MealsNavigationType.BOTTOM_NAVIGATION
        }
        WindowWidthSizeClass.Medium -> {
            MealsNavigationType.NAVIGATION_RAIL
        }
        WindowWidthSizeClass.Expanded -> {
            MealsNavigationType.PERMANENT_NAVIGATION_DRAWER
        }
        else -> {
            MealsNavigationType.BOTTOM_NAVIGATION
        }
    }

    val showBottom = showBottomBar(navController, navigationType)
    Scaffold(
        bottomBar = {
            if (showBottom) {
                NavigationBar(navController, navigationType)
            }
        })
    {
        NavigationHost(navController, navigationType)
    }
}

