package com.example.mealsapp.search

import com.example.domain.entity.MealList

data class SearchState(
    val isLoading: Boolean = false,
    val error: String = "",
    val search: List<MealList>? = emptyList()
)
