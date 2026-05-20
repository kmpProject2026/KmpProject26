package com.itis.kmpproj26.common.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import com.itis.kmpproj26.common.ui.theme.Providers

@Composable
fun DOutlinedInputSection(
    title: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String? = null,
    isError: Boolean = false,
    errorText: String? = null,
    singleLine: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        DText(
            modifier = Modifier.padding(start = Providers.spacing.s),
            text = title,
            style = Providers.typography.bodyL,
            color = Providers.color.onSurface,
        )

        Spacer(modifier = Modifier.height(Providers.spacing.xs))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = value,
            onValueChange = onValueChange,
            singleLine = singleLine,
            isError = isError,
            shape = Providers.shape.l,
            textStyle = Providers.typography.bodyL.copy(
                color = Providers.color.onSurface
            ),
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            visualTransformation = visualTransformation,
            placeholder = {
                if (placeholder != null) {
                    DText(
                        text = placeholder,
                        style = Providers.typography.bodyL,
                        color = Providers.color.outline,
                    )
                }
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = if (isError) Providers.color.error else Providers.color.primary,
                unfocusedBorderColor = if (isError) Providers.color.error else Providers.color.outline,
                errorBorderColor = Providers.color.error,
                cursorColor = if (isError) Providers.color.error else Providers.color.primary,
                focusedContainerColor = Providers.color.surface,
                unfocusedContainerColor = Providers.color.surface,
                errorContainerColor = Providers.color.surface,
                focusedTextColor = Providers.color.onSurface,
                unfocusedTextColor = Providers.color.onSurface,
                errorTextColor = Providers.color.onSurface,
            )
        )

        errorText?.let {
            Spacer(modifier = Modifier.height(Providers.spacing.xs))
            DText(
                modifier = Modifier.padding(start = Providers.spacing.s),
                text = errorText,
                style = Providers.typography.bodyL,
                color = Providers.color.error,
            )
        }
    }
}
