package com.itis.kmpproj26.feature.profile.ui.screen.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import com.itis.kmpproj26.common.ui.component.DText
import com.itis.kmpproj26.common.ui.theme.Providers
import com.itis.kmpproj26.common.ui.theme.designsystem.SeedCream
import kmpproj26native.composeapp.generated.resources.Res
import kmpproj26native.composeapp.generated.resources.app_name
import kmpproj26native.composeapp.generated.resources.ic_dictionary
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun ProfileImageCard() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(Providers.componentSize.iconExtraLarge)
                .border(
                    width = Providers.spacing.xxxs,
                    color = Providers.color.primary,
                    shape = CircleShape
                )
                .clip(CircleShape)
                .background(SeedCream),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(Res.drawable.ic_dictionary),
                contentDescription = stringResource(Res.string.app_name),
                modifier = Modifier
                    .size(Providers.componentSize.iconLarge * 1.4f)
                    .padding(Providers.spacing.m),
                contentScale = ContentScale.Inside
            )
        }

        Spacer(modifier = Modifier.height(Providers.spacing.m))

        DText(
            text = stringResource(Res.string.app_name),
            style = Providers.typography.heading5,
            color = Providers.color.primary,
        )
    }
}
