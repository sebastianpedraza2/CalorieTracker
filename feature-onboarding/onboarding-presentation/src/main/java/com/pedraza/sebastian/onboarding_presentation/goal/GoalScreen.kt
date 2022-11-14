package com.pedraza.sebastian.onboarding_presentation.goal

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pedraza.sebastian.core.utils.UiEvent
import com.pedraza.sebastian.core_ui.LocalSpacing
import com.pedraza.sebastian.core.R
import com.pedraza.sebastian.core.domain.model.GoalType
import com.pedraza.sebastian.onboarding_presentation.components.ActionButton
import com.pedraza.sebastian.onboarding_presentation.components.SelectableButton

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun GoalScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: GoalViewModel = hiltViewModel()
) {
    val selectedActivityLevel by viewModel.selectedGoal.collectAsStateWithLifecycle()
    val spacing = LocalSpacing.current

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Navigate -> onNavigate(event)
                else -> Unit
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing.spaceLarge),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.lose_keep_or_gain_weight),
                style = MaterialTheme.typography.h3
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            Row {
                SelectableButton(
                    text = stringResource(id = R.string.lose),
                    isSelected = selectedActivityLevel is GoalType.LoseWeight,
                    color = MaterialTheme.colors.primaryVariant,
                    selectedTextColor = Color.White,
                    textStyle = MaterialTheme.typography.button.copy(
                        fontWeight = FontWeight.Normal
                    )
                ) {
                    viewModel.onGoalClick(GoalType.LoseWeight)
                }
                Spacer(Modifier.width(30.dp))
                SelectableButton(
                    text = stringResource(id = R.string.keep),
                    isSelected = selectedActivityLevel is GoalType.KeepWeight,
                    color = MaterialTheme.colors.primaryVariant,
                    selectedTextColor = Color.White,
                    textStyle = MaterialTheme.typography.button.copy(
                        fontWeight = FontWeight.Normal
                    )
                ) {
                    viewModel.onGoalClick(GoalType.KeepWeight)
                }
                Spacer(Modifier.width(30.dp))
                SelectableButton(
                    text = stringResource(id = R.string.gain),
                    isSelected = selectedActivityLevel is GoalType.GainWeight,
                    color = MaterialTheme.colors.primaryVariant,
                    selectedTextColor = Color.White,
                    textStyle = MaterialTheme.typography.button.copy(
                        fontWeight = FontWeight.Normal
                    )
                ) {
                    viewModel.onGoalClick(GoalType.GainWeight)
                }

            }
        }
        ActionButton(
            modifier = Modifier.align(Alignment.BottomEnd),
            text = stringResource(id = R.string.next),
            onClick = {
                viewModel.onNextClick()
            })
    }

}