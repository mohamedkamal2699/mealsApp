package com.example.mealsapp.mealDetails

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.common.FROM_FAVORITE_LIST
import com.example.domain.common.IS_FROM_FAVORITE
import com.example.domain.common.MEAL_ID
import com.example.domain.common.Result
import com.example.domain.entity.MealEntity
import com.example.domain.usecase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealDetailsViewModel @Inject constructor(
    private val getMealUS: GetMealInFavorite,
    savedStateHandle: SavedStateHandle,
    private val deleteMeal: DeleteMeal,
    private val getMealUseCase: GetMeal,
    private val insertMealUseCase: InsertMeal,
    private val isExistUseCase: IsExist,
) : ViewModel() {

    private val _meal = MutableStateFlow(MealState())
    val meal = _meal.asStateFlow()

    init {
        val mealId = savedStateHandle.get<String>(MEAL_ID).toString()
        val fromFavorite = savedStateHandle.get<String>(IS_FROM_FAVORITE).toString()

        Log.d("mohamed", "MealDetailsViewModel: $fromFavorite")
        if (fromFavorite == FROM_FAVORITE_LIST) {
            getMealFromFavorite(mealID = mealId)
        } else {
            getMealFromRemote(mealID = mealId)
        }

    }

    fun getMealFromFavorite(mealID: String) = viewModelScope.launch {
        getMealUS(idMeal = mealID).collect { result ->
            when (result) {
                is Result.Error -> {
                    _meal.value = MealState(error = result.msg)
                }
                is Result.Loading -> {
                    _meal.value = MealState(isLoading = true)
                }
                is Result.Success -> {
                    _meal.value = MealState(
                        isLoading = false, inFavorite = true,
                        meal = result.data
                    )
                }
            }
        }
    }

    fun getMealFromRemote(mealID: String) = viewModelScope.launch {

        // Collecting the result from getMealUseCase
        getMealUseCase(idMeal = mealID).collect { result ->
            when (result) {
                is Result.Error -> {
                    // Updating _meal StateFlow with an error state
                    _meal.value = MealState(error = result.msg)
                }
                is Result.Loading -> {
                    // Updating _meal StateFlow with loading state
                    _meal.value = MealState(isLoading = true)
                }
                is Result.Success -> {
                    // Updating _meal StateFlow with successful data retrieval
                    _meal.value = MealState(
                        isLoading = false, inFavorite = false, meal = result.data
                    )
                }
            }
        }
    }

    fun deleteMeal(meal: MealEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                deleteMeal.delete(meal)
            } catch (e: Exception) {
                Log.i("MealViewModel", "Error inserting meal: $e")
            }
        }
    }

    fun exist(idMeal: String): Boolean {
        return isExistUseCase.isExist(idMeal)
    }

    fun insertMeal(meal: MealEntity) {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                insertMealUseCase.insert(meal)
            } catch (e: Exception) {
                Log.i("MealViewModel", "Error inserting meal: $e")
            }
        }
    }

}