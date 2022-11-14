package com.pedraza.sebastian.onboarding_presentation.weight

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pedraza.sebastian.core.R
import com.pedraza.sebastian.core.data.preferences.Preferences
import com.pedraza.sebastian.core.domain.usecases.FilterOutDigitsUseCase
import com.pedraza.sebastian.core.navigation.Route
import com.pedraza.sebastian.core.utils.UiEvent
import com.pedraza.sebastian.core.utils.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class WeightViewModel @Inject constructor(
    private val preferences: Preferences,
) : ViewModel() {

    private val _weight = MutableStateFlow("80")
    val weight = _weight.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()


    fun onWeightChanged(weight: String) {
        if (weight.length <= 5) {
            _weight.value = weight
        }
    }

    fun onClickNext() {
        viewModelScope.launch {
            val weightNumber = _weight.value.toFloatOrNull() ?: kotlin.run {
                _uiEvent.send(
                    UiEvent.ShowSnackBar(
                        UiText.ResourcesString(R.string.error_weight_cant_be_empty)
                    )
                )
                return@launch
            }
            preferences.saveWeight(weightNumber)
            _uiEvent.send(
                UiEvent.Navigate(Route.ACTIVITY)
            )
        }
    }

}