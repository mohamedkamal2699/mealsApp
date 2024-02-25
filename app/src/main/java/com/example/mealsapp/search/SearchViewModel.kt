package com.example.mealsapp.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.common.Result
import com.example.domain.usecase.SearchForMeal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchForMeal: SearchForMeal
) : ViewModel() {

    private val _mealList = MutableStateFlow(SearchState())
    val mealList = _mealList.asStateFlow()


    fun searchMeal(strMeal: String) = viewModelScope.launch {
        searchForMeal(strMeal).collect { result ->
            when (result) {
                is Result.Error -> {
                    _mealList.value = SearchState(error = result.msg)
                }
                is Result.Loading -> {
                    _mealList.value = SearchState(isLoading = true)
                }
                is Result.Success -> {
                    _mealList.value = SearchState(
                        isLoading = false,
                        search = result.data
                    )
                }
            }
        }
    }

}