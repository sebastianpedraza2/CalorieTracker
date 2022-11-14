package com.pedraza.sebastian.onboarding_presentation.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pedraza.sebastian.core.data.preferences.Preferences
import com.pedraza.sebastian.core.domain.model.ActivityLevel
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
class ActivityViewModel @Inject constructor(private val preferences: Preferences) : ViewModel() {

    private val _selectedActivity = MutableStateFlow<ActivityLevel>(ActivityLevel.Medium)
    val selectedActivity = _selectedActivity.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onActivityClick(activity: ActivityLevel) {
        _selectedActivity.value = activity
    }

    fun onNextClick() {
        viewModelScope.launch {
            _uiEvent.send(UiEvent.Navigate(Route.GOAL))
            preferences.saveActivityLevel(_selectedActivity.value)
        }
    }
}