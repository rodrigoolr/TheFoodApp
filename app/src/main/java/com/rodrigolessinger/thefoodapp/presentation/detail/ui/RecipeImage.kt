package com.rodrigolessinger.thefoodapp.presentation.detail.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import dev.chrisbanes.accompanist.coil.CoilImage
import kotlin.math.max

@Composable
fun RecipeImage(
    thumbnail: String,
    imageHeight: Dp,
    scrollValue: Float,
    parallaxFactor: Float = 0.3f,
    fadingEdgeFactor: Float = 0.5f
) {
    val imageHeightPx = with(LocalDensity.current) { imageHeight.toPx() }

    Box(
        modifier = Modifier
            .height(imageHeight)
            .fillMaxWidth()
            .graphicsLayer {
                alpha = max(0f, 1 - (scrollValue / imageHeightPx))
                translationY = -scrollValue * parallaxFactor
            }
    ) {
        CoilImage(
            data = thumbnail,
            contentDescription = "",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxSize(),
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(Color.Transparent, Color.White),
                        imageHeightPx * fadingEdgeFactor,
                    )
                )
        )
    }
}
