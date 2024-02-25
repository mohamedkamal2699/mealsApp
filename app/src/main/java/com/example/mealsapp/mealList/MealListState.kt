package com.example.mealsapp.mealList

import com.example.domain.entity.MealList

data class MealListState(
    val isLoading: Boolean = false,
    val error: String = "",
    val mealList: List<MealList>? = emptyList()
)