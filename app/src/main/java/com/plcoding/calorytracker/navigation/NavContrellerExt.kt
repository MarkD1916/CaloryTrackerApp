package com.plcoding.calorytracker.navigation

import androidx.navigation.NavController
import com.vmakd1916gmail.com.core.util.UiEvent

fun NavController.navigate(event: UiEvent.Navigate){
    this.navigate(event.route)
}