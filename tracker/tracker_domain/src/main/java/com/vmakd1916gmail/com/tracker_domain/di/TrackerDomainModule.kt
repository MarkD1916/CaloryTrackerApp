package com.vmakd1916gmail.com.tracker_domain.di

import com.vmakd1916gmail.com.core.data.preferences.DefaultPreferences
import com.vmakd1916gmail.com.tracker_domain.repository.TrackerRepository
import com.vmakd1916gmail.com.tracker_domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object TrackerDomainModule {

    @ViewModelScoped
    @Provides
    fun provideTrackerUseCases(
        repository: TrackerRepository,
        preferences: DefaultPreferences
    ): TrackerUseCases {
        return TrackerUseCases(
            trackFood = TrackFood(repository),
            searchFood = SearchFood(repository),
            getFoodsForDate = GetFoodsForDate(repository),
            deleteTrackedFood = DeleteTrackedFood(repository),
            calculationsMealNutrients = CalculationsMealNutrients(preferences)
        )
    }

}