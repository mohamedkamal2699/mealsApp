package com.example.mealsapp.mealDetails

import com.example.domain.entity.MealEntity

data class MealState(
    val isLoading: Boolean = false,
    var error: String = "",
    val meal: MealEntity? = null,
    val inFavorite: Boolean? = null
)