package com.example.mealsapp.home


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.common.Result
import com.example.domain.usecase.GetCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel
@Inject constructor(
    private val getMealsUseCase: GetCategory
) : ViewModel() {


    private val _categories = MutableStateFlow(CategoriesState())
    val categories = _categories.asStateFlow()

    init {
        getCategories()
    }

    private fun getCategories() = viewModelScope.launch {
        getMealsUseCase().collect { result ->
            when (result) {
                is Result.Error -> {
                    _categories.value = CategoriesState(error = result.msg)
                }
                is Result.Loading -> {
                    _categories.value = CategoriesState(isLoading = true)
                }
                is Result.Success -> {
                    _categories.value = CategoriesState(
                        isLoading = false,
                        category = result.data
                    )
                }
            }
        }
    }

}
