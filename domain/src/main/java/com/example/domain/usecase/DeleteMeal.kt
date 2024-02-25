package com.example.domain.usecase


import com.example.domain.entity.MealEntity
import com.example.domain.repo.MealsRepoDB
import javax.inject.Inject


class DeleteMeal @Inject constructor(private val mealsRepo: MealsRepoDB) {

    suspend fun delete(meal: MealEntity) = mealsRepo.deleteMeal(meal)
}