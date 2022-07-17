package com.bruno.pokedex.presentation.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.bruno.pokedex.presentation.theme.Primary100
import com.bruno.pokedex.presentation.theme.Secondary100


@Composable
fun DefaultImage(painter: Painter, modifier: Modifier = Modifier) {
    Image(
        painter = painter,
        contentDescription = null,
        modifier = modifier
    )
}

@Composable
fun ColumnWithGradient(
    endY: Float,
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(),
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    colors: List<Color>,
    content: @Composable () -> Unit
) {
    var height = LocalConfiguration.current.screenHeightDp.dp.value
    Column(
        modifier = modifier
            .onGloballyPositioned {
                height = it.size.height.toFloat()
            }
            .background(
                brush = Brush.verticalGradient(
                    colors = colors,
                    endY = (height * endY)
                )
            )
            .padding(paddingValues = paddingValues),
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment
    ) {
        content()
    }
}
