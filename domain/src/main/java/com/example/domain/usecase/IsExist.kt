package com.example.domain.usecase

import com.example.domain.repo.MealsRepoDB
import javax.inject.Inject

class IsExist @Inject constructor(private val mealsRepo: MealsRepoDB) {
    fun isExist(idMeal: String): Boolean = mealsRepo.isExist(idMeal)
}
