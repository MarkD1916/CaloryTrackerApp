package com.vmakd1916gmail.com.tracker_presentation.tracker_overview

import androidx.annotation.DrawableRes
import com.vmakd1916gmail.com.core.util.UiText
import com.vmakd1916gmail.com.tracker_domain.model.MealType
import com.vmakd1916gmail.com.tracker_presentation.R

data class Meal(
    val name: UiText,
    @DrawableRes val drawableRes: Int,
    val mealType: MealType,
    val carbs: Int = 0,
    val protein: Int = 0,
    val fat: Int = 0,
    val calories: Int = 0,
    val isExpanded: Boolean = false
)

val defaultMeals = listOf(
    Meal(
        name = UiText.StringResource(R.string.breakfast),
        drawableRes = R.drawable.breakfast,
        mealType = MealType.Breakfast
    ),
    Meal(
        name = UiText.StringResource(R.string.dinner),
        drawableRes = R.drawable.dinner,
        mealType = MealType.Dinner
    ),
    Meal(
        name = UiText.StringResource(R.string.lunch),
        drawableRes = R.drawable.lunch,
        mealType = MealType.Lunch
    ),
    Meal(
        name = UiText.StringResource(R.string.snacks),
        drawableRes = R.drawable.snack,
        mealType = MealType.Snack
    )
)
