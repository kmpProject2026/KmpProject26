package com.itis.kmpproj26.feature.profile.ui.screen.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.itis.kmpproj26.common.ui.component.DText
import com.itis.kmpproj26.common.ui.theme.Providers

@Composable
fun ProfileSectionCard(
    title: String,
    text: String,
) {
    DText(
        modifier = Modifier.padding(start = Providers.spacing.s),
        text = title,
        style = Providers.typography.bodyL,
        color = Providers.color.onSurfaceVariant,
    )
    Spacer(modifier = Modifier.height(Providers.spacing.xs))

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = Providers.shape.l,
        colors = CardDefaults.cardColors(
            containerColor = Providers.color.surfaceVariant
        )
    ) {
        DText(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Providers.spacing.m),
            style = Providers.typography.bodyL,
            color = Providers.color.onSurface,
            text = text,
        )
    }
}
