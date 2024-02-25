package com.example.data.mapper


import com.example.domain.entity.Meal
import com.example.domain.entity.MealEntity


fun Meal.toMeal(): MealEntity {

    return MealEntity(
        idMeal = idMeal,
        strMeal = strMeal,
        strDrinkAlternate = strDrinkAlternate.toString(),
        strCategory = strCategory,
        strArea = strArea,
        strInstructions = strInstructions,
        strMealThumb = strMealThumb,
        strTags = strTags,
        strYoutube = strYoutube,
        strIngredient1 = strIngredient1.toString(),
        strIngredient2 = strIngredient2.toString(),
        strIngredient3 = strIngredient3.toString(),
        strIngredient4 = strIngredient4.toString(),
        strIngredient5 = strIngredient5.toString(),
        strIngredient6 = strIngredient6.toString(),
        strIngredient7 = strIngredient7.toString(),
        strIngredient8 = strIngredient8.toString(),
        strIngredient9 = strIngredient9.toString(),
        strIngredient10 = strIngredient10.toString(),
        strIngredient11 = strIngredient11.toString(),
        strIngredient12 = strIngredient12.toString(),
        strIngredient13 = strIngredient13.toString(),
        strIngredient14 = strIngredient14.toString(),
        strIngredient15 = strIngredient15.toString(),
        strIngredient16 = strIngredient16.toString(),
        strIngredient17 = strIngredient17.toString(),
        strIngredient18 = strIngredient18.toString(),
        strIngredient19 = strIngredient19.toString(),
        strIngredient20 = strIngredient20.toString(),
        strMeasure1 = strMeasure1.toString(),
        strMeasure2 = strMeasure2.toString(),
        strMeasure3 = strMeasure3.toString(),
        strMeasure4 = strMeasure4.toString(),
        strMeasure5 = strMeasure5.toString(),
        strMeasure6 = strMeasure6.toString(),
        strMeasure7 = strMeasure7.toString(),
        strMeasure8 = strMeasure8.toString(),
        strMeasure9 = strMeasure9.toString(),
        strMeasure10 = strMeasure10.toString(),
        strMeasure11 = strMeasure11.toString(),
        strMeasure12 = strMeasure12.toString(),
        strMeasure13 = strMeasure13.toString(),
        strMeasure14 = strMeasure14.toString(),
        strMeasure15 = strMeasure15.toString(),
        strMeasure16 = strMeasure16.toString(),
        strMeasure17 = strMeasure17.toString(),
        strMeasure18 = strMeasure18.toString(),
        strMeasure19 = strMeasure19.toString(),
        strMeasure20 = strMeasure20.toString()
    )
}