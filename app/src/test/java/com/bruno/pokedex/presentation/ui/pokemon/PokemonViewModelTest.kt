package com.bruno.pokedex.presentation.ui.pokemon

import com.bruno.pokedex.domain.model.Pokemon
import com.bruno.pokedex.domain.model.PokemonDetail
import com.bruno.pokedex.domain.model.PokemonPaginated
import com.bruno.pokedex.domain.usecase.FilterValuesUseCase
import com.bruno.pokedex.domain.usecase.GetPokemonDetailUseCase
import com.bruno.pokedex.domain.usecase.GetPokemonPaginatedUseCase
import com.bruno.pokedex.presentation.ui.detailpokemon.DetailPokemonScreenAction
import com.bruno.pokedex.presentation.ui.detailpokemon.PokemonDetailScreenValue
import com.bruno.pokedex.presentation.ui.pokemon.PokemonScreenAction.CloseClickedAction
import com.bruno.pokedex.presentation.ui.pokemon.PokemonScreenAction.EndReachedAction
import com.bruno.pokedex.presentation.ui.pokemon.PokemonScreenAction.PokemonClickedAction
import com.bruno.pokedex.presentation.ui.pokemon.PokemonScreenAction.RetryClickedAction
import com.bruno.pokedex.presentation.ui.pokemon.PokemonScreenAction.SearchChangedAction
import com.bruno.pokedex.presentation.ui.pokemon.PokemonScreenAction.SearchClickedAction
import com.bruno.pokedex.presentation.ui.pokemon.PokemonScreenUiState.ScreenState
import com.bruno.pokedex.presentation.ui.pokemon.PokemonViewModel.ScreenEvent
import com.bruno.pokedex.util.CoroutineTestRule
import io.mockk.Called
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class PokemonViewModelTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private lateinit var viewModel: PokemonViewModel
    private val screenEvent: (ScreenEvent) -> Unit = mockk(relaxed = true)
    private val screenState: (ScreenState) -> Unit = mockk(relaxed = true)
    private val isLoading: (Boolean) -> Unit = mockk(relaxed = true)
    private val isError: (Boolean) -> Unit = mockk(relaxed = true)
    private val pokemonList: (List<Pokemon>) -> Unit = mockk(relaxed = true)
    private val search: (String) -> Unit = mockk(relaxed = true)
    private val getPokemonUseCase: GetPokemonPaginatedUseCase = mockk(relaxed = true)
    private val filterValuesUseCase: FilterValuesUseCase = mockk(relaxed = true)

    @Before
    fun setup() {
        viewModel = PokemonViewModel(getPokemonUseCase, filterValuesUseCase)
        prepareObservables()
    }

    @After
    fun clear() {
        coroutineTestRule.testScope.cancel()
    }

    @Test
    fun `when setup is called then request is successful`() {
        //Prepare
        val pokemonPaginatedMock = PokemonPaginated.mock()
        coEvery { getPokemonUseCase.execute(PAGE) } returns Result.success(pokemonPaginatedMock)
        //Action
        viewModel.setup()
        //Check
        verify {
            screenState(ScreenState.Loading)
            pokemonList(pokemonPaginatedMock.pokemonList)
            screenState(ScreenState.Success)
        }
    }

    @Test
    fun `when setup is called then request but fails`() {
        //Prepare
        coEvery { getPokemonUseCase.execute(PAGE) } returns Result.failure(Exception())
        //Action
        viewModel.setup()
        //Check
        verify {
            screenState(ScreenState.Loading)
            screenState(ScreenState.Failure)
        }
    }

    @Test
    fun `when onAction is called with SearchClickedAction then pokemonListUpdates`() {
        //Prepare
        val pokemonListMock = listOf(Pokemon.mock(), Pokemon.mock().copy(name = POKEMON_NAME))
        coEvery { filterValuesUseCase.execute(pokemonListMock, SEARCH) } returns listOf(Pokemon.mock())
        viewModel.setupPokemonList(pokemonListMock)
        viewModel.uiState.search.value = SEARCH
        //Action
        viewModel.onEvent(SearchClickedAction)
        //Check
        verify {
            pokemonList(listOf(Pokemon.mock()))
        }
    }

    @Test
    fun `when onAction is called with SearchChangedAction then search updates`() {
        //Action
        viewModel.onEvent(SearchChangedAction(SEARCH))
        //Check
        verify {
            search(SEARCH)
        }
    }

    @Test
    fun `when onAction is called with PokemonClickedAction then screenEvent has NavigateToNextScreen`() {
        //Action
        viewModel.onEvent(PokemonClickedAction(Pokemon.mock()))
        //Check
        verify {
            screenEvent(ScreenEvent.NavigateToScreen(Pokemon.mock()))
        }
    }

    @Test
    fun `when onAction is called with CloseClickedAction then screenEvent has Finish`() {
        //Action
        viewModel.onEvent(CloseClickedAction)
        //Check
        verify {
            screenEvent(ScreenEvent.Finish)
        }
    }

    @Test
    fun `when onAction is called with RetryClickedAction then request but fails`() {
        //Prepare
        coEvery { getPokemonUseCase.execute(PAGE) } returns Result.failure(Exception())
        //Action
        viewModel.onEvent(RetryClickedAction)
        //Check
        verify {
            screenState(ScreenState.Loading)
            screenState(ScreenState.Failure)
        }
    }

    @Test
    fun `when onAction is called with EndReachedAction then request but fails`() {
        //Prepare
        coEvery { getPokemonUseCase.execute(NEXT_PAGE) } returns Result.failure(Exception())
        viewModel.setupCount(COUNT)
        viewModel.setupPage()
        //Action
        viewModel.onEvent(EndReachedAction)
        //Check
        verify {
            isError(true)
            isLoading(false)
        }
    }

    private fun prepareObservables() = coroutineTestRule.testScope.run {
        launch { viewModel.uiState.screenState.collect { screenState(it) } }
        launch { viewModel.uiState.isLoading.collect { isLoading(it) } }
        launch { viewModel.uiState.isError.collect { isError(it) } }
        launch { viewModel.uiState.pokemonList.collect { pokemonList(it) } }
        launch { viewModel.uiState.search.collect { search(it) } }
        launch { viewModel.eventFlow.collect { screenEvent(it) } }
    }

    private companion object {
        const val PAGE = 0
        const val NEXT_PAGE = 1
        const val COUNT = 2345
        const val SEARCH = "bul"
        const val POKEMON_NAME = "Charizard"
    }
}