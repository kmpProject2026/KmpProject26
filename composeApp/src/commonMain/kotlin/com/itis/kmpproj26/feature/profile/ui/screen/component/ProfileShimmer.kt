package com.itis.kmpproj26.feature.profile.ui.screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.itis.kmpproj26.common.ui.theme.Providers
import com.itis.kmpproj26.common.ui.util.rememberShimmerBrush

@Composable
fun ProfileShimmer() {
    val shimmerBrush = rememberShimmerBrush()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = Providers.spacing.m)
    ) {
        Spacer(modifier = Modifier.height(Providers.spacing.l))

        Box(
            modifier = Modifier.size(Providers.componentSize.iconExtraLarge)
                .align(Alignment.CenterHorizontally)
                .clip(CircleShape)
                .background(shimmerBrush)
        )

        Spacer(modifier = Modifier.height(Providers.spacing.m))

        Box(
            modifier = Modifier.height(Providers.typography.heading5.lineHeight.value.dp)
                .fillMaxWidth(0.4f)
                .align(Alignment.CenterHorizontally)
                .clip(Providers.shape.xs)
                .background(shimmerBrush)
        )

        Spacer(modifier = Modifier.height(Providers.spacing.xl))

        ProfileSectionCardShimmer()
        repeat(3) {
            Spacer(modifier = Modifier.height(Providers.spacing.l))
            ProfileSectionCardShimmer()
        }

        Spacer(modifier = Modifier.height(Providers.spacing.xl))

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
                    .height(Providers.componentSize.textFieldMedium)
                    .padding(Providers.spacing.m)
                    .clip(Providers.shape.xs)
                    .background(shimmerBrush)
            )
        }

        Spacer(modifier = Modifier.height(Providers.spacing.xl))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(Providers.componentSize.buttonMedium)
                .clip(CircleShape)
                .background(shimmerBrush)
        )

        Spacer(modifier = Modifier.height(Providers.spacing.s))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(Providers.componentSize.buttonMedium)
                .clip(CircleShape)
                .background(shimmerBrush)
        )

        Spacer(modifier = Modifier.height(Providers.spacing.l))
    }
}

@Composable
private fun ProfileSectionCardShimmer() {
    val shimmerBrush = rememberShimmerBrush()

    Box(
        modifier = Modifier.height(Providers.spacing.m)
            .fillMaxWidth(0.3f)
            .clip(Providers.shape.xs)
            .background(shimmerBrush)
    )

    Spacer(modifier = Modifier.height(Providers.spacing.xs))

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = Providers.shape.l,
        colors = CardDefaults.cardColors(
            containerColor = Providers.color.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Providers.spacing.m)
        ) {
            Box(
                modifier = Modifier.height(Providers.spacing.m)
                    .fillMaxWidth(0.4f)
                    .clip(Providers.shape.xs)
                    .background(shimmerBrush)
            )
        }
    }
}
