package com.vmakd1916gmail.com.tracker_domain.use_case

import com.vmakd1916gmail.com.core.data.preferences.DefaultPreferences
import com.vmakd1916gmail.com.core.domain.model.ActivityLevel
import com.vmakd1916gmail.com.core.domain.model.Gender
import com.vmakd1916gmail.com.core.domain.model.GoalType
import com.vmakd1916gmail.com.core.domain.model.UserInfo
import com.vmakd1916gmail.com.tracker_domain.model.MealType
import com.vmakd1916gmail.com.tracker_domain.model.TrackedFood
import kotlin.math.roundToInt

class CalculationsMealNutrients(
    private val preferences: DefaultPreferences
) {

    operator fun invoke(trackedFood: List<TrackedFood>) : Result {
        val allNutrients = trackedFood
            .groupBy { it.mealType }
            .mapValues { entry ->
                val type = entry.key
                val food = entry.value
                MealNutrients(
                    carbs = food.sumOf { it.carbs },
                    protein = food.sumOf { it.protein },
                    fat = food.sumOf { it.fat },
                    calories = food.sumOf { it.calories },
                    mealType = type
                )
            }
        val carbsTotal = allNutrients.values.sumOf { it.carbs }
        val proteinTotal = allNutrients.values.sumOf { it.protein }
        val fatTotal = allNutrients.values.sumOf { it.fat }
        val caloriesTotal = allNutrients.values.sumOf { it.calories }

        val userInfo = preferences.loadUserInfo()
        val caloryGoal = dailyCaloryRequirement(userInfo)
        /*
        * one gram of carbs and protein = 4 kcal, one gram of fat = 9kcal
        * */
        val carbsGoal = (caloryGoal * userInfo.carbRatio / 4f).roundToInt()
        val proteinGoal = (caloryGoal * userInfo.proteinRatio / 4f).roundToInt()
        val fatGoal = (caloryGoal * userInfo.fatRatio / 9f).roundToInt()

        return Result(
            carbsGoal = carbsGoal,
            proteinGoal = proteinGoal,
            caloriesGoal = caloryGoal,
            fatGoal = fatGoal,
            caloriesTotal = caloriesTotal,
            fatTotal = fatTotal,
            proteinTotal = proteinTotal,
            carbsTotal = carbsTotal,
            mealNutrients = allNutrients
        )
    }

    /*
    * bmr - Basal Metabolic Rate
    * formula from https://www.garnethealth.org/news/basal-metabolic-rate-calculator
    * */
    private fun bmr(userInfo: UserInfo): Int {
        return when(userInfo.gender) {
            is Gender.Male -> {
                (88.362f + 13.397f * userInfo.weight +
                        4.799f * userInfo.height - 5.677f * userInfo.age).roundToInt()
            }
            is Gender.Female ->  {
                (447.593f + 9.247f * userInfo.weight +
                        3.098f * userInfo.height - 4.330 * userInfo.age).roundToInt()
            }
        }
    }

    private fun dailyCaloryRequirement(userInfo: UserInfo): Int {
        val activityFactor = when(userInfo.activityLevel) {
            is ActivityLevel.Low -> 1.2f
            is ActivityLevel.Medium -> 1.3f
            is ActivityLevel.High -> 1.4f
        }
        val caloryExtra = when(userInfo.goalType) {
            is GoalType.LoseWeight -> -500
            is GoalType.KeepWeight -> 0
            is GoalType.GainWeight -> 500
        }
        return (bmr(userInfo) * activityFactor + caloryExtra).roundToInt()
    }

    data class MealNutrients(
        val carbs: Int,
        val protein: Int,
        val fat: Int,
        val calories: Int,
        val mealType: MealType
    )

    data class Result(
        val carbsGoal: Int,
        val proteinGoal: Int,
        val fatGoal: Int,
        val caloriesGoal: Int,
        val carbsTotal: Int,
        val proteinTotal: Int,
        val fatTotal: Int,
        val caloriesTotal: Int,
        val mealNutrients: Map<MealType, MealNutrients>
    )
}