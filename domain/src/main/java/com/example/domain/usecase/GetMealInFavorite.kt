package com.example.domain.usecase

import com.example.domain.repo.MealsRepoDB
import javax.inject.Inject

//import javax.inject.Inject

class GetMealInFavorite
@Inject constructor(private val mealsRepo: MealsRepoDB) {
    suspend operator fun invoke(idMeal: String) = mealsRepo.getMeal(idMeal)
}
