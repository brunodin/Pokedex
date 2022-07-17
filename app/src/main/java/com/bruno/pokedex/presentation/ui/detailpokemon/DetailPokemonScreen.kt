package com.bruno.pokedex.presentation.ui.detailpokemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
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
    val name by uiState.name.collectAsState()
    val image by uiState.image.collectAsState()
    PokedexTheme {
        val ambientHeight =
            with(LocalDensity.current) { LocalConfiguration.current.screenHeightDp.dp.toPx() }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Support100,
                            Secondary100
                        ),
                        endY = ambientHeight * 0.5f
                    )
                )
                .padding(vertical = 10.dp)
        ) {
            IconButton(onClick = { /*TODO*/ }) {
                Image(
                    painter = painterResource(id = R.drawable.ic_arrow_back),
                    contentDescription = null
                )
            }
            Box(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .offset(y = 75.dp)
                        .fillMaxSize()
                        .padding(bottom = 75.dp + 20.dp, start = 20.dp, end = 20.dp)
                        .background(color = Support100, shape = MaterialTheme.shapes.medium)
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
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
                            text = name.replaceFirstChar { it.uppercase() },
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold,
                            color = Primary100
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        CardWithBorder(
                            modifier = Modifier.padding(vertical = 10.dp),
                            backgroundColor = Primary100
                        ) {
                            Text(
                                modifier = Modifier.padding(horizontal = 15.dp, vertical = 5.dp),
                                text = "PODER",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = Support200
                            )
                        }
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        CardWithBorder(
                            shape = RoundedCornerShape(5.dp),
                            modifier = Modifier
                                .padding(top = 10.dp, end = 5.dp, start = 20.dp)
                                .height(40.dp)
                                .weight(1f),
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceEvenly,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_balance),
                                    contentDescription = null
                                )
                                Text(
                                    modifier = Modifier.padding(
                                        horizontal = 15.dp,
                                        vertical = 5.dp
                                    ),
                                    text = "19.5kg",
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Support200
                                )
                            }
                        }
                        CardWithBorder(
                            shape = RoundedCornerShape(5.dp),
                            modifier = Modifier
                                .padding(top = 10.dp, start = 5.dp, end = 20.dp)
                                .height(40.dp)
                                .weight(1f),
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceEvenly,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_height),
                                    contentDescription = null
                                )
                                Text(
                                    modifier = Modifier.padding(
                                        horizontal = 15.dp,
                                        vertical = 5.dp
                                    ),
                                    text = "2.3m",
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Support200
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(30.dp))
                    Text(
                        modifier = Modifier.padding(
                            horizontal = 15.dp,
                            vertical = 5.dp
                        ),
                        text = stringResource(id = R.string.pokemon_details_base_stats),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Support200
                    )
                    Divider(
                        modifier = Modifier.padding(horizontal = 30.dp, vertical = 5.dp),
                        color = Support300,
                        thickness = 1.5.dp,
                    )
                    Row(
                        modifier = Modifier
                            .padding(vertical = 10.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        StatsBar(progress = 0.9f)
                        StatsBar(progress = 0.9f)
                        StatsBar(progress = 0.9f)
                        StatsBar(progress = 0.9f)
                        StatsBar(progress = 0.9f)
                        StatsBar(progress = 0.9f)
                    }
                }
                Image(
                    painter = rememberAsyncImagePainter(model = image),
                    contentDescription = null,
                    modifier = Modifier
                        .size(120.dp)
                        .align(alignment = Alignment.TopCenter)
                )
            }
        }
    }
}

@Composable
fun StatsBar(
    progressColor: Color = Primary100,
    progress: Float
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .graphicsLayer {
                    rotationZ = 180f
                }
        ) {
            Box(
                modifier = Modifier
                    .padding(start = 20.dp)
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
                        .height((progress * 200).dp)
                        .clip(shape = CircleShape)
                        .background(color = progressColor, shape = CircleShape)
                        .border(
                            width = 1.dp,
                            shape = CircleShape,
                            color = Support200
                        )
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "HP",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(top = 10.dp)
                    .graphicsLayer {
                        rotationZ = 90f
                    }
            )
        }

        Text(
            text = "100%",
            fontSize = 13.sp,
            modifier = Modifier
                .padding(top = 5.dp)
        )
    }
}

@Composable
fun CardWithBorder(
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