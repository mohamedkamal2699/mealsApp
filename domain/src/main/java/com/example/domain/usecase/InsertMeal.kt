package com.example.domain.usecase

import com.example.domain.entity.MealEntity
import com.example.domain.repo.MealsRepoDB
import javax.inject.Inject


class InsertMeal @Inject constructor(private val mealsRepo: MealsRepoDB) {

    suspend fun insert(meal: MealEntity) = mealsRepo.insertMeal(meal)
}