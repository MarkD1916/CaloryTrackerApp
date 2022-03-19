package com.vmakd1916gmail.onboarding_domain.use_case

import com.vmakd1916gmail.com.core.util.UiText
import com.vmakd1916gmail.com.core.R

class ValidateNutrients {

    operator fun invoke(
        carbsRatioText: String,
        proteinRationText: String,
        fatRatioText: String
    ): Result {
        val carbsRatio = carbsRatioText.toIntOrNull()
        val proteinRatio = proteinRationText.toIntOrNull()
        val fatRatio = fatRatioText.toIntOrNull()
        if (carbsRatio==null || proteinRatio==null || fatRatio==null){
            return Result.Error(
                message = UiText.StringResource(R.string.error_invalid_values)
            )
        }
        if (carbsRatio + proteinRatio + fatRatio != 100){
            return Result.Error(
                message = UiText.StringResource(R.string.error_not_100_percent)
            )
        }
        return Result.Success(carbsRatio, proteinRatio, fatRatio)
    }

    sealed class Result {
        data class Success(
            val carbsRatio: Int,
            val proteinRatio: Int,
            val fatRatio: Int
        ) : Result()

        data class Error(val message: UiText) : Result()
    }
}