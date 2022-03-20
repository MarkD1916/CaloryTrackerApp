package com.vmakd1916gmail.com.tracker_domain.use_case

import com.vmakd1916gmail.com.tracker_domain.model.TrackedFood
import com.vmakd1916gmail.com.tracker_domain.repository.TrackerRepository

class DeletedTrackedFood(
    private val repository: TrackerRepository
) {

    suspend operator fun invoke(food: TrackedFood) {
        repository.deleteTrackedFood(food)
    }
}