package com.example.mealsapp.navigation


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.mealsapp.R

@Composable
fun NavigationBar(
    navController: NavController,
    navigationType: MealsNavigationType
) {

    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Area,
        BottomNavItem.Search,
        BottomNavItem.Favorite
    )

    if (navigationType == MealsNavigationType.NAVIGATION_RAIL) {

        NavigationRail(Modifier.background(MaterialTheme.colorScheme.surfaceVariant)) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .background(color = Color(0x00FFFFFF)),
                verticalArrangement = Arrangement.spacedBy(
                    dimensionResource(id = R.dimen.navigation_rail_items_spacedBy),
                    Alignment.CenterVertically
                )
            ) {
                items.forEach { item ->
                    NavigationRailItem(
                        selected = currentRoute == item.route,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.startDestinationId)
                                launchSingleTop = true
                            }
                        },
                        icon = { Icon(item.icon, contentDescription = null) },
                        label = { Text(item.label) },
                        alwaysShowLabel = false
                    )
                }
            }
        }
    }
    if (navigationType == MealsNavigationType.PERMANENT_NAVIGATION_DRAWER) {
        PermanentNavigationDrawer(
            drawerContent = {
                PermanentDrawerSheet(Modifier.width(dimensionResource(R.dimen.drawer_width))) {
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .background(color = Color(0x00FFFFFF)),
                        verticalArrangement = Arrangement.spacedBy(
                            dimensionResource(id = R.dimen.navigation_rail_items_spacedBy),
                            Alignment.CenterVertically
                        )
                    ) {
                        items.forEach { item ->
                            NavigationDrawerItem(
                                selected = currentRoute == item.route,
                                onClick = {
                                    navController.navigate(item.route) {
                                        popUpTo(navController.graph.startDestinationId)
                                        launchSingleTop = true
                                    }
                                },
                                icon = { Icon(item.icon, contentDescription = null) },
                                label = { Text(item.label) }
                            )
                        }
                    }
                }
            }
        ) {

        }
    }
    if (navigationType == MealsNavigationType.BOTTOM_NAVIGATION) {
        NavigationBar(
            modifier = Modifier
                .clip(
                    shape = RoundedCornerShape(
                        topStart = dimensionResource(id = R.dimen.bottom_bar_roundedCornerShape),
                        topEnd = dimensionResource(id = R.dimen.bottom_bar_roundedCornerShape)
                    )
                )
        ) {
            items.forEach { item ->
                NavigationBarItem(
                    selected = currentRoute == item.route,
                    onClick = {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                    },
                    icon = { Icon(item.icon, contentDescription = null) },
                    label = { Text(item.label) }
                )
            }
        }
    }

}


@Composable
fun showBottomBar(navController: NavController, navigationType: MealsNavigationType): Boolean {
    val currentRoute =
        navController.currentBackStackEntryAsState().value?.destination?.route

    return when (navigationType) {
        MealsNavigationType.BOTTOM_NAVIGATION -> {
            currentRoute in listOf(
                BottomNavItem.Home.route,
                BottomNavItem.Area.route,
                BottomNavItem.Favorite.route,
                BottomNavItem.Search.route
            )
        }
        MealsNavigationType.NAVIGATION_RAIL -> {
            currentRoute in listOf(
                BottomNavItem.Home.route,
                BottomNavItem.Area.route,
                BottomNavItem.Favorite.route,
                BottomNavItem.Search.route
            )
        }
        MealsNavigationType.PERMANENT_NAVIGATION_DRAWER -> {
            true
        }
    }

}


