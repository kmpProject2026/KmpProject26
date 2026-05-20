package com.itis.kmpproj26.feature.profile.ui.screen.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.itis.kmpproj26.common.ui.component.DText
import com.itis.kmpproj26.common.ui.theme.Providers
import com.itis.kmpproj26.feature.profile.ui.screen.ProfileState
import kmpproj26native.composeapp.generated.resources.Res
import kmpproj26native.composeapp.generated.resources.profile_created
import kmpproj26native.composeapp.generated.resources.profile_email
import kmpproj26native.composeapp.generated.resources.profile_first_name
import kmpproj26native.composeapp.generated.resources.profile_last_name
import org.jetbrains.compose.resources.stringResource

@Composable
fun ProfileInfo(
    state: ProfileState,
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        ProfileSectionCard(
            title = stringResource(Res.string.profile_first_name),
            text = state.firstName
        )

        Spacer(modifier = Modifier.height(Providers.spacing.l))

        ProfileSectionCard(
            title = stringResource(Res.string.profile_last_name),
            text = state.lastName
        )

        Spacer(modifier = Modifier.height(Providers.spacing.l))

        ProfileSectionCard(
            title = stringResource(Res.string.profile_email),
            text = state.email
        )

        Spacer(modifier = Modifier.height(Providers.spacing.xl))

        ProfileInfo(
            text = stringResource(Res.string.profile_created, state.createdAt)
        )
    }
}

@Composable
private fun ProfileInfo(
    text: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = Providers.shape.l,
        colors = CardDefaults.cardColors(
            containerColor = Providers.color.surfaceVariant
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Providers.spacing.xxs),
            contentAlignment = Alignment.Center
        ) {
            DText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Providers.spacing.m),
                style = Providers.typography.titleMedium,
                color = Providers.color.primary,
                text = text
            )
        }
    }
}
