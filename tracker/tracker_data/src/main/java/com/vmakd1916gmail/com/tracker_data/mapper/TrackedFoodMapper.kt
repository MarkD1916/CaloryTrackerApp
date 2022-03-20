package com.vmakd1916gmail.com.tracker_data.mapper

import com.vmakd1916gmail.com.tracker_data.local.entity.TrackedFoodEntity
import com.vmakd1916gmail.com.tracker_domain.model.MealType
import com.vmakd1916gmail.com.tracker_domain.model.TrackedFood
import java.time.LocalDate

fun TrackedFoodEntity.toTrackedFood(): TrackedFood {
    return TrackedFood(
        name = name,
        carbs = carbs,
        protein = protein,
        fat = fat,
        imageUrl = imageUrl,
        mealType = MealType.fromString(type),
        amount = amount,
        date = LocalDate.of(year, month, dayOfMonth),
        calories = calories,
        id = id
    )
}

fun TrackedFood.toTrackedFoodEntity(): TrackedFoodEntity {
    return TrackedFoodEntity(
        name = name,
        carbs = carbs,
        protein = protein,
        fat = fat,
        imageUrl = imageUrl,
        type = mealType.name,
        amount = amount,
        month = date.monthValue,
        dayOfMonth = date.dayOfMonth,
        year = date.year,
        calories = calories,
        id = id
    )
}