package com.bruno.pokedex.presentation.ui.detailpokemon

import com.bruno.pokedex.domain.model.PokemonDetail
import com.bruno.pokedex.domain.usecase.GetPokemonDetailUseCase
import com.bruno.pokedex.presentation.ui.detailpokemon.DetailPokemonScreenAction.BackClickedAction
import com.bruno.pokedex.presentation.ui.detailpokemon.DetailPokemonScreenAction.CloseClickedAction
import com.bruno.pokedex.presentation.ui.detailpokemon.DetailPokemonScreenAction.RetryClickedAction
import com.bruno.pokedex.presentation.ui.detailpokemon.DetailPokemonScreenUiState.ScreenState
import com.bruno.pokedex.presentation.ui.detailpokemon.DetailPokemonViewModel.ScreenEvent
import com.bruno.pokedex.util.CoroutineTestRule
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
class DetailPokemonViewModelTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private lateinit var viewModel: DetailPokemonViewModel
    private val screenEvent: (ScreenEvent) -> Unit = mockk(relaxed = true)
    private val screenState: (ScreenState) -> Unit = mockk(relaxed = true)
    private val pokemonDetail: (PokemonDetailScreenValue) -> Unit = mockk(relaxed = true)
    private val getPokemonDetailUseCase: GetPokemonDetailUseCase = mockk(relaxed = true)

    @Before
    fun setup() {
        viewModel = DetailPokemonViewModel(getPokemonDetailUseCase)
        prepareObservables()
    }

    @After
    fun clear() {
        coroutineTestRule.testScope.cancel()
    }

    @Test
    fun `when setup is called then request is successful`() {
        //Prepare
        coEvery { getPokemonDetailUseCase.execute(ID) } returns Result.success(PokemonDetail.mock())
        //Action
        viewModel.setup(ID)
        //Check
        verify {
            screenState(ScreenState.Loading)
            pokemonDetail(PokemonDetailScreenValue.mock())
            screenState(ScreenState.Success)
        }
    }

    @Test
    fun `when setup is called then request but fails`() {
        //Prepare
        coEvery { getPokemonDetailUseCase.execute(ID) } returns Result.failure(Exception())
        //Action
        viewModel.setup(ID)
        //Check
        verify {
            screenState(ScreenState.Loading)
            screenState(ScreenState.Failure)
        }
    }

    @Test
    fun `when onAction is called with BackClickedAction then screenEvent has GoBack`() {
        //Action
        viewModel.onEvent(BackClickedAction)
        //Check
        verify {
            screenEvent(ScreenEvent.GoBack)
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
        coEvery { getPokemonDetailUseCase.execute(ID) } returns Result.failure(Exception())
        viewModel.setupId(ID)
        //Action
        viewModel.onEvent(RetryClickedAction)
        //Check
        verify {
            screenState(ScreenState.Loading)
            screenState(ScreenState.Failure)
        }
    }

    private fun prepareObservables() = coroutineTestRule.testScope.run {
        launch { viewModel.uiState.pokemonDetail.collect { pokemonDetail(it) } }
        launch { viewModel.uiState.screenState.collect { screenState(it) } }
        launch { viewModel.eventFlow.collect { screenEvent(it) } }
    }

    private companion object {
        const val ID = 1
    }
}