package com.example.mealsapp.favorite

import com.example.domain.entity.MealEntity

data class FavoriteState(
    val isLoading: Boolean = false,
    var error: String = "",
    val meal: List<MealEntity>? = emptyList()
)
