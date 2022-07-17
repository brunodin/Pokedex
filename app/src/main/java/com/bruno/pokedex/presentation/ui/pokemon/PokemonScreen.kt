package com.bruno.pokedex.presentation.ui.pokemon

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.SubcomposeAsyncImage
import com.bruno.pokedex.R
import com.bruno.pokedex.domain.model.Pokemon
import com.bruno.pokedex.presentation.theme.PokedexTheme
import com.bruno.pokedex.presentation.theme.Primary100
import com.bruno.pokedex.presentation.theme.Secondary100
import com.bruno.pokedex.presentation.theme.Support100
import com.bruno.pokedex.presentation.theme.Support200
import com.bruno.pokedex.presentation.theme.Support300
import com.bruno.pokedex.presentation.ui.pokemon.PokemonScreenAction.PokemonClickedAction
import com.bruno.pokedex.presentation.ui.pokemon.PokemonScreenAction.SearchChangedAction
import com.bruno.pokedex.presentation.ui.pokemon.PokemonScreenAction.SearchClickedAction
import com.bruno.pokedex.presentation.ui.pokemon.PokemonScreenUiState.ScreenState
import com.bruno.pokedex.presentation.ui.pokemon.PokemonViewModel.ScreenEvent
import kotlinx.coroutines.flow.collect

private const val COLUMN_GRID = 2

@Composable
fun PokemonScreen(
    viewModel: PokemonViewModel = hiltViewModel(),
    onNextScreen: (Pokemon) -> Unit
) {
    LaunchedEffect(key1 = Unit) { viewModel.setup() }
    Screen(uiState = viewModel.uiState, onEvent = viewModel::onEvent)
    EventConsumer(viewModel = viewModel, onNextScreen = onNextScreen)
}

@Composable
private fun EventConsumer(
    viewModel: PokemonViewModel,
    onNextScreen: (Pokemon) -> Unit
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.eventFlow.collect { event ->
            when(event) {
                is ScreenEvent.NavigateToScreen -> { onNextScreen(event.pokemon) }
            }
        }
    }
}

@Composable
private fun Screen(
    uiState: PokemonScreenUiState,
    onEvent: (PokemonScreenAction) -> Unit
) {
    val screenState by uiState.screenState.collectAsState()
    PokedexTheme {
        when (screenState) {
            ScreenState.Failure -> {}
            ScreenState.Loading -> {}
            ScreenState.Success -> ScreenSuccess(uiState = uiState, onEvent = onEvent)
        }
    }
}

@Composable
private fun ScreenSuccess(
    uiState: PokemonScreenUiState,
    onEvent: (PokemonScreenAction) -> Unit
) {
    val pokemonList by uiState.pokemonList.collectAsState()
    LazyColumn {
        item {
            ColumnWithGradient {
                DefaultImage(painter = painterResource(id = R.drawable.ic_logo))
                TextFieldWithBorder(uiState = uiState, onEvent = onEvent)
            }
        }
        items(items = pokemonList.chunked(COLUMN_GRID)) { pokemonList ->
            PokemonGrid(pokemonList = pokemonList, onEvent = onEvent)
        }
    }
}

@Composable
private fun PokemonGrid(
    pokemonList: List<Pokemon>,
    onEvent: (PokemonScreenAction) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(space = 10.dp),
    ) {
        pokemonList.forEach { pokemon ->
            Box(modifier = Modifier.weight(1f, fill = true)) {
                PokemonCard(pokemon = pokemon, onEvent = onEvent)
            }
        }
        if (pokemonList.size != COLUMN_GRID) Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
private fun PokemonCard(
    pokemon: Pokemon,
    onEvent: (PokemonScreenAction) -> Unit
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .padding(top = 20.dp)
            .aspectRatio(0.9f)
            .clickable(onClick = { onEvent(PokemonClickedAction(pokemon)) }),
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
                    text = pokemon.name.replaceFirstChar { it.uppercase() },
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Support100
                )
            }
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                SubcomposeAsyncImage(
                    modifier = Modifier.size(100.dp),
                    model = pokemon.imageUrl,
                    loading = {
                        CircularProgressIndicator(
                            color = Secondary100,
                            modifier = Modifier.scale(0.5f)
                        )
                    },
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
private fun DefaultImage(painter: Painter, modifier: Modifier = Modifier) {
    Image(
        painter = painter,
        contentDescription = null,
        modifier = modifier
    )
}

@Composable
private fun ColumnWithGradient(content: @Composable () -> Unit) {
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
        content()
    }
}

@Composable
private fun TextFieldWithBorder(
    uiState: PokemonScreenUiState,
    onEvent: (PokemonScreenAction) -> Unit
) {
    val search by uiState.search.collectAsState()
    Box {
        var isFocused by remember { mutableStateOf(false) }
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .onFocusEvent { isFocused = it.isFocused },
            value = search,
            onValueChange = { onEvent(SearchChangedAction(it)) },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = Support100,
                unfocusedBorderColor = Support200,
                focusedBorderColor = Support200,
                focusedLabelColor = Support200
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                capitalization = KeyboardCapitalization.Sentences,
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(onSearch = { onEvent(SearchClickedAction) }),
            leadingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = null
                )
            },
            label = {
                if (isFocused.not() && search.isEmpty()) Text(text = stringResource(id = R.string.pokemon_search))
            }
        )
    }
}