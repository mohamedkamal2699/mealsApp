package com.example.domain.repo


import com.example.domain.common.Result
import com.example.domain.entity.Area
import com.example.domain.entity.Category
import com.example.domain.entity.MealEntity
import com.example.domain.entity.MealList
import kotlinx.coroutines.flow.Flow

interface MealsRepo {
    suspend fun getCategoriesFromRemote(): Flow<Result<List<Category>>>
    suspend fun getMealListFR(map: Map<String, String>): Flow<Result<List<MealList>>>
    suspend fun searchFR(strMeal: String): Flow<Result<List<MealList>>>
    suspend fun getMealFR(idMeal: String): Flow<Result<MealEntity>>
    suspend fun getAreaFR(): Flow<Result<List<Area>>>
}