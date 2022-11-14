package com.pedraza.sebastian.onboarding_presentation.goal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pedraza.sebastian.core.data.preferences.Preferences
import com.pedraza.sebastian.core.domain.model.GoalType
import com.pedraza.sebastian.core.navigation.Route
import com.pedraza.sebastian.core.utils.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class GoalViewModel @Inject constructor(private val preferences: Preferences) : ViewModel() {

    private val _selectedGoal = MutableStateFlow<GoalType>(GoalType.KeepWeight)
    val selectedGoal = _selectedGoal.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onGoalClick(goal: GoalType) {
        _selectedGoal.value = goal
    }

    fun onNextClick() {
        viewModelScope.launch {
            _uiEvent.send(UiEvent.Navigate(Route.NUTRIENT_GOAL))
            preferences.saveGoalType(_selectedGoal.value)
        }
    }
}