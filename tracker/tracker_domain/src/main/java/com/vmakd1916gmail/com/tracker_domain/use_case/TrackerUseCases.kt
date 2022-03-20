package com.vmakd1916gmail.com.tracker_domain.use_case

data class TrackerUseCases(
    val trackFood: TrackFood,
    val searchFood: SearchFood,
    val getFoodsForDate: GetFoodsForDate,
    val deletedTrackedFood: DeletedTrackedFood,
    val calculationsMealNutrients: CalculationsMealNutrients
)