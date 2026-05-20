package com.itis.kmpproj26.common.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.window.Dialog
import com.itis.kmpproj26.common.ui.theme.Providers
import kmpproj26native.composeapp.generated.resources.Res
import kmpproj26native.composeapp.generated.resources.common_error
import kmpproj26native.composeapp.generated.resources.common_ok
import kmpproj26native.composeapp.generated.resources.ic_info
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import ru.fav.yogadaily.ui.component.DIcon

@Composable
fun DDialog(
    message: String,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    title: String = stringResource(Res.string.common_error),
    confirmButtonText: String = stringResource(Res.string.common_ok),
    dismissButtonText: String? = null,
    onConfirm: (() -> Unit)? = null,
    backgroundColor: Color = Providers.color.secondaryContainer,
    contentColor: Color = Providers.color.onSurface,
    iconTint: Color = Providers.color.onSurface,
    shape: Shape = Providers.shape.l,
) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            modifier = modifier,
            color = backgroundColor,
            contentColor = contentColor,
            shape = shape
        ) {
            Column(
                modifier = Modifier.padding(Providers.spacing.l)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    DIcon(
                        painter = painterResource(Res.drawable.ic_info),
                        contentDescription = null,
                        tint = iconTint,
                        modifier = Modifier.padding(end = Providers.spacing.m)
                    )

                    DText(
                        text = title,
                        style = Providers.typography.bodyXL,
                        color = contentColor,
                        contentPadding = PaddingValues(
                            vertical = Providers.spacing.m,
                            horizontal = Providers.spacing.none
                        )
                    )
                }

                Spacer(modifier = Modifier.height(Providers.spacing.m))

                DText(
                    text = message,
                    style = Providers.typography.bodyL,
                    color = contentColor.copy(alpha = 0.8f),
                    contentPadding = PaddingValues(Providers.spacing.none)
                )

                Spacer(modifier = Modifier.height(Providers.spacing.l))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    dismissButtonText?.let { text ->
                        TextButton(onClick = onDismiss) {
                            DText(
                                text = dismissButtonText,
                                color = contentColor.copy(alpha = 0.7f),
                                style = Providers.typography.bodyL,
                                contentPadding = PaddingValues(Providers.spacing.none)
                            )
                        }
                        Spacer(modifier = Modifier.width(Providers.spacing.xs))
                    }

                    TextButton(
                        onClick = { onConfirm?.invoke() ?: onDismiss() },
                    ) {
                        DText(
                            text = confirmButtonText,
                            color = Providers.color.primary,
                            style = Providers.typography.bodyL,
                            contentPadding = PaddingValues(Providers.spacing.none)
                        )
                    }
                }
            }
        }
    }
}
