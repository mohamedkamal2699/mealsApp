package com.example.mealsapp.home

import com.example.domain.entity.Category

data class CategoriesState(
    val isLoading: Boolean = false,
    val error: String = "",
    val category: List<Category>? = emptyList()
)
