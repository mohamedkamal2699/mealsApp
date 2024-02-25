package com.example.mealsapp.area

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.common.Result
import com.example.domain.usecase.GetArea
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AreaViewModel
@Inject constructor(private val getAreaUS: GetArea) : ViewModel() {


    private val _area = MutableStateFlow(AreaState())
    val area = _area.asStateFlow()

    init {
        getArea()
    }

    private fun getArea() = viewModelScope.launch {
        getAreaUS().collect { result ->
            when (result) {
                is Result.Error -> {
                    _area.value = AreaState(error = result.msg)
                }
                is Result.Loading -> {
                    _area.value = AreaState(isLoading = true)
                }
                is Result.Success -> {
                    _area.value = AreaState(
                        isLoading = false,
                        area = result.data
                    )
                }
            }
        }
    }

}