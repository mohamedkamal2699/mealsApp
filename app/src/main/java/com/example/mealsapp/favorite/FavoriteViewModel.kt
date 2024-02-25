package com.example.mealsapp.favorite

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.common.Result
import com.example.domain.entity.MealEntity
import com.example.domain.usecase.DeleteMeal
import com.example.domain.usecase.GetFavorite
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel
@Inject constructor(
    private val getFavorite: GetFavorite,
    private val deleteMeal: DeleteMeal
) : ViewModel() {


    private val _mealList = MutableStateFlow(FavoriteState())
    var mealList = _mealList.asStateFlow()

    init {
        getMeals()
    }

    private fun getMeals() = viewModelScope.launch {
        getFavorite().collect { result ->
            when (result) {
                is Result.Error -> {
                    _mealList.value = FavoriteState(error = result.msg)
                }
                is Result.Loading -> {
                    _mealList.value = FavoriteState(isLoading = true)
                }
                is Result.Success -> {
                    _mealList.value = FavoriteState(
                        isLoading = false,
                        meal = result.data
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
}