package com.example.domain.usecase

import com.example.domain.repo.MealsRepo
import javax.inject.Inject


class GetMealList @Inject constructor(private val mealsRepo: MealsRepo) {
    suspend operator fun invoke(map: Map<String, String>) = mealsRepo.getMealListFR(map)

}