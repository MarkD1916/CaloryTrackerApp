package com.vmakd1916gmail.com.tracker_presentation.search

import com.vmakd1916gmail.com.tracker_domain.model.TrackableFood

data class TrackableFoodUiState(
    val food: TrackableFood,
    val isExpanded: Boolean = false,
    val amount: String = ""
)