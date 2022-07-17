package com.bruno.pokedex.util

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout

private const val FIRST_INDEX = 0

fun Modifier.vertical() = layout { measurable, constraints ->
        val placeable = measurable.measure(constraints)
        layout(placeable.height, placeable.width) {
            placeable.place(
                x = -(placeable.width / 2 - placeable.height / 2),
                y = -(placeable.height / 2 - placeable.width / 2)
            )
        }
    }

@Composable
fun rememberEndReachedState(onEndReached: () -> Unit): LazyListState {
    val scrollState = rememberLazyListState()
    val shouldLoadMore = remember {
        derivedStateOf {
            val lastVisibleItem = scrollState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: FIRST_INDEX

            val isEndReached = lastVisibleItem == scrollState.layoutInfo.totalItemsCount.dec() && lastVisibleItem != FIRST_INDEX
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