package com.example.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.domain.entity.MealEntity

@Dao
interface MealsDataBaseDao {
    @Insert
    suspend fun insertMeal(meal: MealEntity)

    @Delete
    suspend fun deleteMeal(meal: MealEntity)

    @Query("SELECT * FROM meals_table")
    suspend fun getMeals(): List<MealEntity>

    @Query("SELECT * FROM meals_table WHERE idMeal IN (:idMeal)")
    suspend fun getMeal(idMeal: String): MealEntity

    @Query("SELECT * FROM meals_table WHERE idMeal IN (:idMeal)")
    fun isExist(idMeal: String): Boolean


}
