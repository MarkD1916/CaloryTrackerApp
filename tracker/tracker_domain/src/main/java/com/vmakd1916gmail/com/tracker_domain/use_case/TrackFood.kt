package com.vmakd1916gmail.com.tracker_domain.use_case

import com.vmakd1916gmail.com.tracker_domain.model.MealType
import com.vmakd1916gmail.com.tracker_domain.model.TrackableFood
import com.vmakd1916gmail.com.tracker_domain.model.TrackedFood
import com.vmakd1916gmail.com.tracker_domain.repository.TrackerRepository
import java.time.LocalDate
import kotlin.math.roundToInt

class TrackFood(
    private val repository: TrackerRepository
) {

    suspend operator fun invoke(
        food: TrackableFood,
        amount: Int,
        mealType: MealType,
        date: LocalDate
    ) {
        val totalValueCarbs = getTotalValue(amount, food.carbsPer100gram)
        val totalValueProtein = getTotalValue(amount, food.proteinPer100gram)
        val totalValueFat = getTotalValue(amount, food.fatPer100gram)
        val totalValueCalories = getTotalValue(amount, food.caloriesPer100gram)


        repository.insertTrackedFood(
            TrackedFood(
                name = food.name,
                carbs = totalValueCarbs,
                protein = totalValueProtein,
                fat = totalValueFat,
                calories = totalValueCalories,
                amount = amount,
                mealType = mealType,
                date = date,
                imageUrl = food.imageUrl
            )
        )
    }

    fun getTotalValue(amount: Int, valuePer100gram: Int): Int {
        return ((valuePer100gram / 100f) * amount).roundToInt()
    }
}