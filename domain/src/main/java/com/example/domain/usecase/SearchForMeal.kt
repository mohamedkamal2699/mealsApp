package com.example.domain.usecase

import com.example.domain.repo.MealsRepo
import javax.inject.Inject

class SearchForMeal @Inject constructor(private val mealsRepo: MealsRepo) {
    suspend operator fun invoke(strMeal: String) = mealsRepo.searchFR(strMeal)
}