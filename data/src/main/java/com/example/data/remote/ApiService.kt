package com.example.data.remote

import com.example.domain.entity.AreaResponse
import com.example.domain.entity.CategoryResponse
import com.example.domain.entity.MealListResponse
import com.example.domain.entity.MealResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface ApiService {

    @GET("categories.php")
    suspend fun getCategories(): CategoryResponse

    @GET("list.php?a=list")
    suspend fun getArea(): AreaResponse


    @GET("filter.php")
    suspend fun mealList(@QueryMap map: Map<String, String>): MealListResponse

    @GET("lookup.php")
    suspend fun getMeal(@Query("i") idMeal: String): MealResponse

    @GET("search.php")
    suspend fun search(@Query("s") strMeal: String): MealListResponse

}