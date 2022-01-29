package com.vmakd1916gmail.com.core.domain.model

data class UserInfo(
    val gender: Gender,
    val age: Int,
    val weight: Float,
    val height: Float,
    val activityLevel: ActivityLevel,
    val goalType: GoalType,
    val carpRatio: Float,
    val proteinRatio: Float,
    val fatRatio: Float
)
