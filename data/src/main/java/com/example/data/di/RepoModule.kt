package com.example.data.di

import com.example.data.database.MealsDataBaseDao
import com.example.data.remote.ApiService
import com.example.data.repo.DBRepoImpl
import com.example.data.repo.MealsRepoImpl
import com.example.domain.repo.MealsRepo
import com.example.domain.repo.MealsRepoDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Provides
    fun provideRepo(apiService: ApiService): MealsRepo {
        return MealsRepoImpl(apiService)
    }

    @Provides
    fun provideRepoDB(mealsDataBaseDao: MealsDataBaseDao): MealsRepoDB {
        return DBRepoImpl(mealsDataBaseDao)
    }

}