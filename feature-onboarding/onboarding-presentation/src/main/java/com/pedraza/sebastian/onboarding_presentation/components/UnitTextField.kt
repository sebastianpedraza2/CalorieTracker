package com.pedraza.sebastian.onboarding_presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import com.pedraza.sebastian.core_ui.LocalSpacing

@Composable
fun UnitTextField(
    text: String,
    unitText: String,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle(
        color = MaterialTheme.colors.primaryVariant,
        fontSize = 70.sp
    ),
    onValueChanged: (String) -> Unit
) {
    val spacing = LocalSpacing.current
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {
        BasicTextField(
            value = text,
            onValueChange = onValueChanged,
            textStyle = textStyle,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            modifier = Modifier
                .width(intrinsicSize = IntrinsicSize.Min)
                .alignBy(LastBaseline)
        )
        Spacer(modifier = modifier.width(spacing.spaceSmall))
        Text(text = unitText, modifier = Modifier.alignBy(LastBaseline))
    }
}