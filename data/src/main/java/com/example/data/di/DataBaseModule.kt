package com.example.data.di

import android.app.Application
import androidx.room.Room
import com.example.data.database.MealsDataBase
import com.example.data.database.MealsDataBaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun provideRoomDatabaseBuilder(application: Application): MealsDataBase {

        return Room.databaseBuilder(
            application,
            MealsDataBase::class.java, "meal_db"
        )
            .allowMainThreadQueries().build()
    }


    @Provides
    @Singleton
    fun provideUserDaoInstance(mealsDataBase: MealsDataBase): MealsDataBaseDao {
        return mealsDataBase.mealsDataBaseDao
    }
}