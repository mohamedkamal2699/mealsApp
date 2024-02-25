package com.example.mealsapp.mealList

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.common.FILTER_CHAR
import com.example.domain.common.MEAL_LIST_NAME
import com.example.domain.common.Result
import com.example.domain.usecase.GetMealList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MealListViewModel
@Inject constructor(
    private val getMealListUC: GetMealList, savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _mealList = MutableStateFlow(MealListState())
    val mealList = _mealList.asStateFlow()

    init {
        val listName = savedStateHandle.get<String>(MEAL_LIST_NAME).toString()
        val listChar = savedStateHandle.get<String>(FILTER_CHAR).toString()
        getMealCategory(map = mapOf(listChar to listName))
    }

    private fun getMealCategory(map: Map<String, String>) = viewModelScope.launch {

        getMealListUC(map).collect { result ->
            when (result) {
                is Result.Error -> {
                    _mealList.value = MealListState(error = result.msg)
                }
                is Result.Loading -> {
                    _mealList.value = MealListState(isLoading = true)
                }
                is Result.Success -> {
                    _mealList.value = MealListState(
                        isLoading = false,
                        mealList = result.data
                    )
                }
            }
        }
    }

}