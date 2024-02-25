package com.example.domain.repo


import com.example.domain.common.Result
import com.example.domain.entity.MealEntity
import kotlinx.coroutines.flow.Flow

interface MealsRepoDB {
    suspend fun insertMeal(meal: MealEntity)
    suspend fun deleteMeal(meal: MealEntity)
    suspend fun getFavorite(): Flow<Result<List<MealEntity>>>
    suspend fun getMeal(idMeal: String): Flow<Result<MealEntity>>
    fun isExist(idMeal: String): Boolean
}