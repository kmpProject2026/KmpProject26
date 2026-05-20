package com.itis.kmpproj26.feature.profile.ui.screen.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.itis.kmpproj26.common.ui.component.DButton
import com.itis.kmpproj26.common.ui.theme.Providers
import com.itis.kmpproj26.feature.profile.ui.screen.ProfileState
import kmpproj26native.composeapp.generated.resources.Res
import kmpproj26native.composeapp.generated.resources.profile_delete_profile
import kmpproj26native.composeapp.generated.resources.profile_logout
import org.jetbrains.compose.resources.stringResource

@Composable
fun ProfileContent(
    state: ProfileState,
    onLogoutClick: () -> Unit,
    onDeleteProfileClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(horizontal = Providers.spacing.m)
    ) {
        Spacer(modifier = modifier.height(Providers.spacing.l))

        ProfileImageCard()

        Spacer(modifier = modifier.height(Providers.spacing.xl))

        ProfileInfo(state = state)

        Spacer(modifier = modifier.height(Providers.spacing.xl))

        DButton(
            text = stringResource(Res.string.profile_logout),
            onClick = onLogoutClick,
        )

        Spacer(modifier = modifier.height(Providers.spacing.s))

        DButton(
            text = stringResource(Res.string.profile_delete_profile),
            onClick = onDeleteProfileClick,
            containerColor = Providers.color.secondaryContainer,
            contentColor = Providers.color.onSecondaryContainer
        )

        Spacer(modifier = modifier.height(Providers.spacing.l))
    }
}

