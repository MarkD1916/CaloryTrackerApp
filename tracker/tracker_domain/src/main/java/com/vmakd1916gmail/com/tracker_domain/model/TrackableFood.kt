package com.vmakd1916gmail.com.tracker_domain.model

data class TrackableFood(
    val name: String,
    val imageUrl : String?,
    val caloriesPer100gram: Int,
    val carbsPer100gram: Int,
    val proteinPer100gram: Int,
    val fatPer100gram: Int
)