package com.bruno.pokedex.presentation.ui.detailpokemon

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.bruno.pokedex.R
import com.bruno.pokedex.presentation.theme.PokedexTheme
import com.bruno.pokedex.presentation.theme.Primary100
import com.bruno.pokedex.presentation.theme.Secondary100
import com.bruno.pokedex.presentation.theme.Support100
import com.bruno.pokedex.presentation.theme.Support200
import com.bruno.pokedex.presentation.theme.Support300
import com.bruno.pokedex.presentation.ui.common.ColumnWithGradient
import com.bruno.pokedex.presentation.ui.common.DefaultImage
import com.bruno.pokedex.presentation.ui.detailpokemon.mapper.color
import com.bruno.pokedex.presentation.ui.detailpokemon.mapper.stats
import com.bruno.pokedex.util.vertical

@Composable
fun DetailPokemonScreen(
    viewModel: DetailPokemonViewModel = hiltViewModel(),
    pokemonId: Int
) {
    LaunchedEffect(key1 = Unit) { viewModel.setup(pokemonId) }
    Screen(uiState = viewModel.uiState)
}

@Composable
private fun Screen(uiState: DetailPokemonScreenUiState) {
    val pokemonDetail by uiState.pokemonDetail.collectAsState()
    PokedexTheme {
        ColumnWithGradient(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            endY = 0.5f,
            colors = listOf(Support100, Secondary100),
            ) {
            IconButton(onClick = { /*TODO*/ }) {
                DefaultImage(painter = painterResource(id = R.drawable.ic_arrow_back))
            }
            Box(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .offset(y = 75.dp)
                        .padding(end = 20.dp, start = 20.dp, bottom = 20.dp)
                        .background(color = Support100, shape = MaterialTheme.shapes.medium),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    PokemonCardName(uiState = uiState)
                    PokemonType(uiState = uiState)
                    PokemonStatsCard(uiState = uiState)
                    Spacer(modifier = Modifier.height(30.dp))
                    BaseStatSText()
                    DividerBaseStats()
                    StatusBarRow(uiState = uiState)
                }
                DefaultImage(
                    painter = rememberAsyncImagePainter(model = pokemonDetail.imageUrl),
                    modifier = Modifier
                        .size(120.dp)
                        .align(alignment = Alignment.TopCenter)
                )
            }
        }
    }
}

@Composable
private fun BaseStatSText() {
    Text(
        modifier = Modifier.padding(horizontal = 15.dp, vertical = 5.dp),
        text = stringResource(id = R.string.pokemon_details_base_stats),
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        color = Support200
    )
}

@Composable
private fun DividerBaseStats() {
    Divider(
        modifier = Modifier.padding(horizontal = 30.dp, vertical = 5.dp),
        color = Support300,
        thickness = 1.5.dp,
    )
}

@Composable
private fun StatusBarRow(uiState: DetailPokemonScreenUiState) {
    val pokemonDetail by uiState.pokemonDetail.collectAsState()
    Row(
        modifier = Modifier
            .padding(vertical = 10.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        pokemonDetail.baseStats.forEach { baseStat ->
            StatsBar(
                progress = baseStat.percentage.toFloat(),
                name = baseStat.stats.stats(),
                progressColor = baseStat.stats.color()
            )
        }
    }
}

@Composable
private fun PokemonType(uiState: DetailPokemonScreenUiState) {
    val pokemonDetail by uiState.pokemonDetail.collectAsState()
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        pokemonDetail.types.forEach { type ->
            CardWithBorder(
                modifier = Modifier.padding(vertical = 10.dp),
                backgroundColor = Primary100
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 15.dp, vertical = 5.dp),
                    text = type,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Support200
                )
            }
        }
    }
}

@Composable
private fun PokemonStatsCard(uiState: DetailPokemonScreenUiState) {
    val pokemonDetail by uiState.pokemonDetail.collectAsState()
    val iconTextList = listOf(
        R.drawable.ic_balance to stringResource(id = R.string.pokemon_details_kg, pokemonDetail.weight),
        R.drawable.ic_height to stringResource(id = R.string.pokemon_details_meter, pokemonDetail.height)
    )
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        iconTextList.forEach { iconText ->
            CardWithBorder(
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier
                    .padding(top = 10.dp, end = 5.dp, start = 20.dp)
                    .height(40.dp)
                    .weight(1f),
            ) {
                IconText(
                    icon = painterResource(id = iconText.first),
                    text = iconText.second
                )
            }
        }
    }
}

@Composable
private fun IconText(icon: Painter, text: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        DefaultImage(painter = icon)
        Text(
            modifier = Modifier.padding(
                horizontal = 15.dp,
                vertical = 5.dp
            ),
            text = text,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = Support200
        )
    }
}

@Composable
private fun PokemonCardName(uiState: DetailPokemonScreenUiState) {
    val pokemonDetail by uiState.pokemonDetail.collectAsState()
    Box(
        modifier = Modifier
            .padding(all = 3.dp)
            .fillMaxWidth()
            .height(75.dp + 30.dp)
            .background(
                color = Secondary100,
                shape = MaterialTheme.shapes.medium
            ),
        contentAlignment = Alignment.BottomCenter
    ) {
        Text(
            modifier = Modifier.padding(bottom = 20.dp),
            text = pokemonDetail.name,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            color = Primary100
        )
    }
}

@Composable
private fun StatsBar(
    progressColor: Color = Primary100,
    name: String,
    progress: Float
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.rotate(180f)
        ) {
            Box(
                modifier = Modifier
                    .height(200.dp)
                    .width(15.dp)
                    .clip(shape = CircleShape)
                    .background(color = Support100, shape = CircleShape)
                    .border(
                        width = 1.dp,
                        shape = CircleShape,
                        color = Support200
                    )
            ) {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(((progress/100) * 200).dp)
                        .clip(shape = CircleShape)
                        .background(color = progressColor, shape = CircleShape)
                        .border(
                            width = 1.dp,
                            shape = CircleShape,
                            color = Support200
                        )
                )
            }
            Spacer(modifier = Modifier.width(2.dp))
            Text(
                text = name,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .vertical()
                    .rotate(90f)
                    .padding(start = 5.dp)
            )
        }
        Text(
            text = progress.toString(),
            fontSize = 13.sp,
            modifier = Modifier
                .padding(top = 5.dp)
                .offset(x = 7.dp)
        )
    }
}

@Composable
private fun CardWithBorder(
    modifier: Modifier = Modifier,
    shape: Shape = CircleShape,
    backgroundColor: Color = Support100,
    content: @Composable () -> Unit,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .shadow(5.dp, shape = shape)
            .clip(shape)
            .background(backgroundColor)
            .border(width = 1.dp, color = Support200, shape = shape)
    ) {
        content()
    }
}