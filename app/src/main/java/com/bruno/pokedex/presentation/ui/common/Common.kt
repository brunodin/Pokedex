package com.bruno.pokedex.presentation.ui.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bruno.pokedex.R
import com.bruno.pokedex.presentation.theme.Support100
import com.bruno.pokedex.presentation.theme.Support200


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

@Composable
fun IconImageButton(
    onClick: () -> Unit,
    painter: Painter,
    modifier: Modifier = Modifier
) {
    IconButton(onClick = onClick) {
        DefaultImage(painter = painter, modifier = modifier)
    }
}

@Composable
fun ScreenError(
    onRetryClicked: () -> Unit,
    onErrorCloseClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 20.dp),
        verticalArrangement = Arrangement.spacedBy(space = 10.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            IconImageButton(
                onClick = onErrorCloseClicked,
                painter = painterResource(id = R.drawable.ic_close)
            )
        }
        DefaultImage(
            painter = painterResource(id = R.drawable.ic_not_disturb),
            modifier = Modifier
                .size(80.dp)
                .align(alignment = Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = stringResource(id = R.string.pokemon_error_title),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
        )
        Text(
            text = stringResource(id = R.string.pokemon_error_subtitle),
            fontSize = 20.sp,
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            onClick = onRetryClicked,
            shape = RoundedCornerShape(25.dp),
            border = BorderStroke(width = 1.5.dp, color = Support200),
            colors = ButtonDefaults.buttonColors(backgroundColor = Support100)
        ) {
            DefaultImage(painter = painterResource(id = R.drawable.ic_reload))
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = stringResource(id = R.string.pokemon_error_button), color = Support200)
        }
    }
}

@Composable
fun ScreenLoading() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(100.dp)
                .scale(0.8f)
        )
    }
}
