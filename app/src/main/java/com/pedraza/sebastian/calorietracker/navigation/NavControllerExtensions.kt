package com.pedraza.sebastian.calorietracker.navigation

import androidx.navigation.NavController
import com.pedraza.sebastian.core.utils.UiEvent

fun NavController.navigate(event: UiEvent.Navigate) {
    this.navigate(event.route)
}