package com.example.domain.usecase

import com.example.domain.repo.MealsRepoDB
import javax.inject.Inject


class GetFavorite @Inject constructor(private val mealsRepo: MealsRepoDB) {

    suspend operator fun invoke() = mealsRepo.getFavorite()
}