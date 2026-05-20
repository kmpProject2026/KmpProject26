package com.itis.kmpproj26.common.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import com.itis.kmpproj26.common.ui.theme.Providers
import ru.fav.yogadaily.ui.component.DIcon

@Composable
fun DButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    containerColor: Color = Providers.color.primary,
    contentColor: Color = Providers.color.onPrimary,
    icon: Painter? = null,
    contentDescription: String? = null,
    enabled: Boolean = true,
    contentPadding: PaddingValues = PaddingValues(horizontal = Providers.spacing.m),
) {
    Button(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor,
            disabledContainerColor = containerColor.copy(alpha = 0.38f),
            disabledContentColor = contentColor.copy(alpha = 0.38f)
        ),
        contentPadding = contentPadding
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            icon?.let {
                DIcon(
                    painter = icon,
                    contentDescription = contentDescription,
                    tint = contentColor,
                    size = Providers.componentSize.iconSmall
                )

                Spacer(modifier = modifier.width(Providers.spacing.xs))
            }


            DText(
                text = text,
                color = contentColor
            )
        }
    }
}
