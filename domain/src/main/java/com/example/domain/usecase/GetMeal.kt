package com.example.domain.usecase

import com.example.domain.repo.MealsRepo
import javax.inject.Inject


class GetMeal @Inject constructor(private val mealsRepo: MealsRepo) {
    suspend operator fun invoke(idMeal: String) = mealsRepo.getMealFR(idMeal)
}
