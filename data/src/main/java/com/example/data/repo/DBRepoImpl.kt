package com.example.data.repo


import com.example.data.database.MealsDataBaseDao
import com.example.domain.common.Result
import com.example.domain.entity.MealEntity
import com.example.domain.repo.MealsRepoDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class DBRepoImpl @Inject constructor(private val mealsDataBaseDao: MealsDataBaseDao) : MealsRepoDB {

    // Common function to fetch data from the local database and handle errors
    private suspend fun <T> fetchDataFromDB(dbCall: suspend () -> T): Flow<Result<T>> = flow {
        emit(Result.Loading)

        try {
            val result = dbCall()
            emit(Result.Success(result))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun insertMeal(meal: MealEntity) {
        mealsDataBaseDao.insertMeal(meal)
    }

    override suspend fun deleteMeal(meal: MealEntity) {
        mealsDataBaseDao.deleteMeal(meal)
    }

    override suspend fun getMeal(idMeal: String): Flow<Result<MealEntity>> =
        fetchDataFromDB {
            mealsDataBaseDao.getMeal(idMeal)
        }

    override suspend fun getFavorite(): Flow<Result<List<MealEntity>>> =
        fetchDataFromDB {
            mealsDataBaseDao.getMeals()
        }

    override fun isExist(idMeal: String): Boolean {
        return mealsDataBaseDao.isExist(idMeal)
    }
}