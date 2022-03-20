package com.vmakd1916gmail.com.tracker_data.repository

import com.vmakd1916gmail.com.tracker_data.local.dao.TrackerDao
import com.vmakd1916gmail.com.tracker_data.mapper.toTrackableFood
import com.vmakd1916gmail.com.tracker_data.mapper.toTrackedFood
import com.vmakd1916gmail.com.tracker_data.mapper.toTrackedFoodEntity
import com.vmakd1916gmail.com.tracker_data.remote.OpenFoodApi
import com.vmakd1916gmail.com.tracker_domain.model.TrackableFood
import com.vmakd1916gmail.com.tracker_domain.model.TrackedFood
import com.vmakd1916gmail.com.tracker_domain.repository.TrackerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.lang.Exception
import java.time.LocalDate

class TrackerRepositoryImpl(
    private val dao: TrackerDao,
    private val api: OpenFoodApi
) : TrackerRepository {
    override suspend fun searchFood(
        query: String,
        page: Int,
        pageSize: Int
    ): Result<List<TrackableFood>> {
        return try {
            val searchDto = api.searchFood(
                query = query,
                page = page,
                pageSize = pageSize
            )
            Result.success(
                searchDto.products.mapNotNull { it.toTrackableFood() }
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    override suspend fun insertTrackedFood(food: TrackedFood) {
        dao.insertTrackedFood(food.toTrackedFoodEntity())
    }

    override suspend fun deleteTrackedFood(food: TrackedFood) {
        dao.deleteTrackedFood(food.toTrackedFoodEntity())
    }

    override fun getFoodsForDate(localData: LocalDate): Flow<List<TrackedFood>> {
        return dao.getFoodsForDate(
            day = localData.dayOfMonth,
            month = localData.monthValue,
            year = localData.year
        ).map { entities ->
            entities.map { it.toTrackedFood() }
        }
    }
}