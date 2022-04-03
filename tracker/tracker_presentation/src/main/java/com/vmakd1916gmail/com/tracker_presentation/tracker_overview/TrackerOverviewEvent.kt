package com.vmakd1916gmail.com.tracker_presentation.tracker_overview

import com.vmakd1916gmail.com.tracker_domain.model.TrackedFood

sealed class TrackerOverviewEvent {
    object onNextDayClick: TrackerOverviewEvent()
    object onPreviousDayClick: TrackerOverviewEvent()
    data class OnToggleMealClick(
        val meal: Meal
    ): TrackerOverviewEvent()
    data class OnDeleteTrackedFoodClick(val trackedFood: TrackedFood): TrackerOverviewEvent()
    data class OnAddFoodClick(val meal: Meal): TrackerOverviewEvent()
}