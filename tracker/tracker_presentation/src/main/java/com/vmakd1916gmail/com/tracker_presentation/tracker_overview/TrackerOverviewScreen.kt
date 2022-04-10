package com.vmakd1916gmail.com.tracker_presentation.tracker_overview

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.vmakd1916gmail.com.core.util.UiEvent
import com.vmakd1916gmail.com.core_ui.LocalSpacing
import com.vmakd1916gmail.com.tracker_presentation.R
import com.vmakd1916gmail.com.tracker_presentation.components.ExpandableMeal
import com.vmakd1916gmail.com.tracker_presentation.tracker_overview.components.AddButton
import com.vmakd1916gmail.com.tracker_presentation.tracker_overview.components.DaySelector
import com.vmakd1916gmail.com.tracker_presentation.tracker_overview.components.NutrientsHeader
import com.vmakd1916gmail.com.tracker_presentation.tracker_overview.components.TrackedFoodItem
import java.nio.file.WatchEvent

@Composable
fun TrackerOverviewScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: TrackerOverviewViewModel = hiltViewModel()
) {
    val spacing = LocalSpacing.current
    val state = viewModel.state
    val context = LocalContext.current
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = spacing.spaceMedium)
    ) {
        item {
            NutrientsHeader(state = state)
            DaySelector(
                date = state.date,
                onPreviousDayClick = {
                    viewModel.onEvent(TrackerOverviewEvent.onPreviousDayClick)
                },
                onNextDayClick = {
                    viewModel.onEvent(TrackerOverviewEvent.onNextDayClick)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = spacing.spaceMedium)
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
        }
        items(state.meals) { meal ->
            ExpandableMeal(
                meal = meal,
                onToggleClick = {
                    viewModel.onEvent(TrackerOverviewEvent.OnToggleMealClick(meal))
                },
                content = {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = spacing.spaceSmall)
                    ) {
                        state.trackedFood.forEach { food ->
                            TrackedFoodItem(trackedFood = food, onDeleteClick = {
                                viewModel.onEvent(
                                    TrackerOverviewEvent
                                        .OnDeleteTrackedFoodClick(food)
                                )
                            }
                            )
                            Spacer(modifier = Modifier.height(spacing.spaceMedium))
                        }
                        AddButton(
                            text = stringResource(
                                id = R.string.add_meal,
                                meal.name.asString(context)
                            ),
                            onClick = {
                                viewModel.onEvent(
                                    TrackerOverviewEvent.OnAddFoodClick(meal)
                                )
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}