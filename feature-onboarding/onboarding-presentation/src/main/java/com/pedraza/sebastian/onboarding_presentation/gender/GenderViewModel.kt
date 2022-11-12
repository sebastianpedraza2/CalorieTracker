package com.pedraza.sebastian.onboarding_presentation.gender

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pedraza.sebastian.core.domain.model.Gender
import com.pedraza.sebastian.core.domain.preferences.Preferences
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
class GenderViewModel @Inject constructor(private val preferences: Preferences) : ViewModel() {

//    var _selectedGender = mutableStateOf<Gender>(Gender.Male)
//        private set

    private val _selectedGender = MutableStateFlow<Gender>(Gender.Male)
    val selectedGender = _selectedGender.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onGenderClick(gender: Gender) {
        _selectedGender.value = gender
    }

    fun onNextClick() {
        viewModelScope.launch {
            _uiEvent.send(UiEvent.Navigate(Route.AGE))
            preferences.saveGender(_selectedGender.value)
        }
    }
}