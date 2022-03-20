package com.vmakd1916gmail.com.tracker_domain.repository

import com.vmakd1916gmail.com.tracker_domain.model.TrackableFood
import com.vmakd1916gmail.com.tracker_domain.model.TrackedFood
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface TrackerRepository {

    suspend fun searchFood(
        query: String,
        page: Int,
        pageSize: Int
    ): Result<List<TrackableFood>>

    suspend fun insertTrackedFood(food: TrackedFood)

    suspend fun deleteTrackedFood(food: TrackedFood)

    fun getFoodsForDate(localData: LocalDate): Flow<List<TrackedFood>>
}