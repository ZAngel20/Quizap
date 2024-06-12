package com.politecnico.quizap.Components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginTextField(

    modifier: Modifier = Modifier,
    textFieldValue: MutableState<String>,
    textLabel: String,
    maxChar: Int? = null,
    capitalization: KeyboardCapitalization = KeyboardCapitalization.None,
    keyboardType: KeyboardType,
    keyboardActions: KeyboardActions,
    imeAction: ImeAction,
    trailingIcon: @Composable() (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    ProvideTextStyle(TextStyle(color = Color.White)) {
        TextField(
            modifier = modifier
                .fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0xFF4472c0),
                cursorColor = Color.White,
                disabledLabelColor = Color(0xFF4472c0),
                focusedIndicatorColor = Color.White.copy(alpha = 0.25f),
                unfocusedIndicatorColor = Color.White.copy(alpha = 0.25f)
            ),
            value = textFieldValue.value.take(maxChar ?: 40),
            onValueChange = { textFieldValue.value = it },
            label = {
                Text(text = textLabel)
            },
            trailingIcon = trailingIcon,
            keyboardOptions = KeyboardOptions(
                capitalization = capitalization,
                keyboardType = keyboardType,
                imeAction = imeAction
            ),
            keyboardActions = keyboardActions,
            visualTransformation = visualTransformation,
        )
        }
    }

