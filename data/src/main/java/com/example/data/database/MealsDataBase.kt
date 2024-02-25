package com.example.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.domain.entity.MealEntity


@Database(entities = [MealEntity::class], version = 1, exportSchema = false)
abstract class MealsDataBase : RoomDatabase() {
    abstract val mealsDataBaseDao: MealsDataBaseDao
}
