package com.bruno.pokedex.util

import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout

private const val FIRST_INDEX = 0

fun Modifier.drawVertical() = layout { measurable, constraints ->
    val placeable = measurable.measure(constraints)
    val width = placeable.height
    val height = placeable.width
    layout(width = width, height = height) {
        val widthMiddle = width / 2
        val heightMiddle = height / 2
        placeable.place(
            x = widthMiddle - heightMiddle,//colocar o meio do texto "width" no meio da largura do Text,
            y = heightMiddle - widthMiddle//coloca o meio do texto "height" no meio da altura do Text
        )
    }
}.rotate(90f)

fun Modifier.drawGradient(colors: List<Color>, endY: Float) = composed {
    var height by remember { mutableStateOf(0f) }
    layout { measurable, constraints ->
        val placeable = measurable.measure(constraints)
        height = placeable.height.toFloat()
        layout(height = placeable.height, width = placeable.width) {
            placeable.place(x = 0, y = 0)
        }
    }.background(
        brush = Brush.verticalGradient(
            colors = colors,
            endY = height * endY
        )
    )
}


@Composable
fun rememberEndReachedState(onEndReached: () -> Unit): LazyListState {
    val scrollState = rememberLazyListState()
    val shouldLoadMore = remember {
        derivedStateOf {
            val lastVisibleItem =
                scrollState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: FIRST_INDEX

            val isEndReached =
                lastVisibleItem == scrollState.layoutInfo.totalItemsCount.dec() && lastVisibleItem != FIRST_INDEX
            Pair(isEndReached, lastVisibleItem)
        }
    }

    LaunchedEffect(shouldLoadMore) {
        snapshotFlow { shouldLoadMore.value }.collect {
            if (it.first) onEndReached()
        }
    }
    return scrollState
}