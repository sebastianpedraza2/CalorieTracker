package com.pedraza.sebastian.onboarding_presentation.age

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pedraza.sebastian.core.data.preferences.Preferences
import com.pedraza.sebastian.core.domain.usecases.FilterOutDigitsUseCase
import com.pedraza.sebastian.core.utils.UiEvent
import com.pedraza.sebastian.core.utils.UiText
import com.pedraza.sebastian.core.R
import com.pedraza.sebastian.core.navigation.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AgeViewModel @Inject constructor(
    private val preferences: Preferences,
    private val filterOutDigitsUseCase: FilterOutDigitsUseCase
) : ViewModel() {

    private val _age = MutableStateFlow("20")
    val age = _age.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()


    fun onAgeChanged(age: String) {
        if (age.length <= 3) {
            _age.value = filterOutDigitsUseCase.invoke(age)
        }
    }

    fun onClickNext() {
        viewModelScope.launch {
            val ageNumber = _age.value.toIntOrNull() ?: kotlin.run {
                _uiEvent.send(
                    UiEvent.ShowSnackBar(
                        UiText.ResourcesString(R.string.error_age_cant_be_empty)
                    )
                )
                return@launch
            }
            preferences.saveAge(ageNumber)
            _uiEvent.send(
                UiEvent.Navigate(Route.HEIGHT)
            )
        }
    }

}