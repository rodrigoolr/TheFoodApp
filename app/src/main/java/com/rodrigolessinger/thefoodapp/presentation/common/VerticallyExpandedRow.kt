package com.rodrigolessinger.thefoodapp.presentation.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.max

enum class SlotsEnum {
    First, Final
}

@Composable
fun VerticallyExpandedRow(
    modifier: Modifier = Modifier,
    itemPadding: Dp = 0.dp,
    content: @Composable () -> Unit
) {
    SubcomposeLayout(modifier = modifier) { constraints ->
        // First measurement pass to find maxHeight
        val measurables = subcompose(SlotsEnum.First, content = content)

        val itemPaddingPx = itemPadding.toPx().toInt()
        // Padding is applied only between elements
        val totalPadding = itemPaddingPx * (measurables.size - 1)
        val maxWidth = constraints.maxWidth - totalPadding

        val targetWidth = maxWidth / measurables.size

        var maxHeight = 0
        var accumulatedWidth = 0
        val itemsWidth = IntArray(measurables.size) { 0 }

        measurables.forEachIndexed { index, measurable ->
            val itemWidth = if (index == measurables.size - 1) {
                // Use all remaining width on last element to avoid blank space
                maxWidth - accumulatedWidth
            } else {
                targetWidth
            }

            val placeable = measurable.measure(
                constraints.copy(
                    minWidth = itemWidth,
                    maxWidth = itemWidth
                )
            )

            maxHeight = max(maxHeight, placeable.height)
            accumulatedWidth += placeable.width
            itemsWidth[index] = placeable.width
        }

        // Second and definitive measurement pass
        val placeables = subcompose(SlotsEnum.Final, content = content)
            .mapIndexed { index, measurable ->
                measurable.measure(
                    constraints = constraints.copy(
                        minWidth = itemsWidth[index],
                        maxWidth = itemsWidth[index],
                        minHeight = maxHeight,
                        maxHeight = maxHeight
                    )
                )
            }

        var xPosition = 0

        layout(constraints.maxWidth, maxHeight) {
            // Place children in the parent layout
            placeables.forEach { placeable ->
                // Position item on the screen
                placeable.placeRelative(x = xPosition, y = 0)

                // Record the x co-ord placed up to
                xPosition += placeable.width + itemPaddingPx
            }
        }
    }
}
