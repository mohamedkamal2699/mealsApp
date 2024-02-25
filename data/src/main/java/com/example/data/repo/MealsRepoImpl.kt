package com.example.data.repo

import com.example.data.mapper.toMeal
import com.example.data.remote.ApiService
import com.example.domain.common.Result
import com.example.domain.entity.Area
import com.example.domain.entity.Category
import com.example.domain.entity.MealEntity
import com.example.domain.entity.MealList
import com.example.domain.repo.MealsRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class MealsRepoImpl @Inject constructor(private val apiService: ApiService) : MealsRepo {

    // Common function to fetch data from the remote API and handle errors
    private suspend fun <T> fetchDataFromRemote(apiCall: suspend () -> T): Flow<Result<T>> = flow {
        emit(Result.Loading)
        try {
            val result = apiCall()
            emit(Result.Success(result))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)


    override suspend fun getCategoriesFromRemote(): Flow<Result<List<Category>>> =
        fetchDataFromRemote {
            apiService.getCategories().categories.map { it.copy() }
        }


    override suspend fun getAreaFR(): Flow<Result<List<Area>>> =
        fetchDataFromRemote {
            apiService.getArea().meals.map { it.copy() }
        }

    override suspend fun getMealListFR(map: Map<String, String>): Flow<Result<List<MealList>>> =
        fetchDataFromRemote {
            apiService.mealList(map).meals.map { it.copy() }
        }

    override suspend fun searchFR(strMeal: String): Flow<Result<List<MealList>>> =
        fetchDataFromRemote {
            apiService.search(strMeal).meals.map { it.copy() }
        }

    override suspend fun getMealFR(idMeal: String): Flow<Result<MealEntity>> =
        fetchDataFromRemote {
            apiService.getMeal(idMeal).meals.single().toMeal()
        }
}
