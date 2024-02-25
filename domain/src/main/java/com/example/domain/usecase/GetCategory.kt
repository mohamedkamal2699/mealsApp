package com.example.domain.usecase

import com.example.domain.repo.MealsRepo
import javax.inject.Inject


class GetCategory @Inject constructor(private val mealsRepo: MealsRepo) {
    suspend operator fun invoke() = mealsRepo.getCategoriesFromRemote()
}