package com.example.mealsapp.area

import com.example.domain.entity.Area

data class AreaState(
    val isLoading: Boolean = false,
    val error: String = "",
    val area: List<Area>? = emptyList()
)