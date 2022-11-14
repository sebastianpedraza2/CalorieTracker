package com.pedraza.sebastian.onboarding_presentation.height

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
class HeightViewModel @Inject constructor(
    private val preferences: Preferences,
    private val filterOutDigitsUseCase: FilterOutDigitsUseCase
) : ViewModel() {

    private val _height = MutableStateFlow("180")
    val height = _height.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()


    fun onHeightChanged(height: String) {
        if (height.length <= 3) {
            _height.value = filterOutDigitsUseCase.invoke(height)
        }
    }

    fun onClickNext() {
        viewModelScope.launch {
            val heightNumber = _height.value.toIntOrNull() ?: kotlin.run {
                _uiEvent.send(
                    UiEvent.ShowSnackBar(
                        UiText.ResourcesString(R.string.error_height_cant_be_empty)
                    )
                )
                return@launch
            }
            preferences.saveHeight(heightNumber)
            _uiEvent.send(
                UiEvent.Navigate(Route.WEIGHT)
            )
        }
    }

}