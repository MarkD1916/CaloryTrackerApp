package com.vmakd1916gmail.com.tracker_data.mapper

import com.vmakd1916gmail.com.tracker_data.remote.dto.Product
import com.vmakd1916gmail.com.tracker_domain.model.TrackableFood
import kotlin.math.roundToInt

fun Product.toTrackableFood(): TrackableFood? {
    val carbsPer100gram = nutriments.carbohydrates100g.roundToInt()
    val proteinPer100gram = nutriments.proteins100g.roundToInt()
    val fatPer100gram = nutriments.fat100g.roundToInt()
    val caloriesPer100gram = nutriments.energyKcal100g.roundToInt()

    return TrackableFood(
        name = productName ?: return null,
        carbsPer100gram = carbsPer100gram,
        proteinPer100gram = proteinPer100gram,
        caloriesPer100gram = caloriesPer100gram,
        fatPer100gram = fatPer100gram,
        imageUrl = imageFrontThumbUrl
    )
}