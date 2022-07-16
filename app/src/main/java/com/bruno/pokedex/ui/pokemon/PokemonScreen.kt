package com.bruno.pokedex.ui.pokemon

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bruno.pokedex.R
import com.bruno.pokedex.theme.PokedexTheme
import com.bruno.pokedex.theme.Primary100
import com.bruno.pokedex.theme.Secondary100
import com.bruno.pokedex.theme.Support100
import com.bruno.pokedex.theme.Support200
import com.bruno.pokedex.theme.Support300

@Composable
fun PokemonScreen() {
    Screen()
}

@Composable
private fun Screen() {
    PokedexTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
        ) {
            var height = LocalConfiguration.current.screenHeightDp.dp.value
            Column(
                modifier = Modifier
                    .onGloballyPositioned {
                        height = it.size.height.toFloat()
                    }
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Primary100,
                                Secondary100
                            ),
                            endY = (height * 0.6f)
                        )
                    )
                    .padding(all = 20.dp),
                verticalArrangement = Arrangement.spacedBy(space = 40.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_logo),
                    contentDescription = null
                )
                Box {
                    var isFocused by remember { mutableStateOf(false) }
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .onFocusEvent { isFocused = it.isFocused },
                        value = "",
                        onValueChange = {},
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            backgroundColor = Support100,
                            unfocusedBorderColor = Support200,
                            focusedBorderColor = Support200,
                            focusedLabelColor = Support200
                        ),
                        leadingIcon = {
                            Image(
                                painter = painterResource(id = R.drawable.ic_search),
                                contentDescription = null
                            )
                        },
                        label = {
                            if (isFocused.not()) Text(text = stringResource(id = R.string.pokemon_search))
                        }
                    )
                }
            }
            PokemonList()
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun PokemonList() {
    val list: List<Int> = (1..15).map { it }

    list.chunked(2).forEach {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(space = 10.dp),

            ) {
            it.forEach {
                PokemonCard()
            }
            if (it.size != 2) Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
private fun RowScope.PokemonCard() {
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .padding(top = 20.dp)
            .aspectRatio(0.9f)
            .weight(1f, fill = true),
        border = BorderStroke(width = 2.dp, color = Support300),
        elevation = 10.dp
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Secondary100, shape = RoundedCornerShape(10.dp))
                    .size(58.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Pokemon",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Support100
                )
            }
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.image),
                    modifier = Modifier.size(100.dp),
                    contentDescription = null
                )
            }
        }
    }
}

