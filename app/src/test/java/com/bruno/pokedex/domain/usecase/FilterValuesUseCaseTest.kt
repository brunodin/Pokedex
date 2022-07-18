package com.bruno.pokedex.domain.usecase

import com.bruno.pokedex.domain.model.Pokemon
import com.bruno.pokedex.util.CoroutineTestRule
import com.bruno.pokedex.util.EMPTY_STRING
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class FilterValuesUseCaseTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private lateinit var usecase: FilterValuesUseCase

    @Before
    fun setup() {
        usecase = FilterValuesUseCase(coroutineTestRule.testDispatcher)
    }

    @Test
    fun `when execute is called then filter is successful`() = runTest {
        //Prepare
        val pokemonListMock = listOf(Pokemon.mock(), Pokemon.mock().copy(name = POKEMON_NAME))
        //Action
        val result = usecase.execute(pokemonListMock, CORRECT_SEARCH)
        //Check
        assertEquals(listOf(pokemonListMock.last()), result)
    }

    @Test
    fun `when execute is called then filter is empty`() = runTest {
        //Prepare
        val pokemonListMock = listOf(Pokemon.mock(), Pokemon.mock().copy(name = POKEMON_NAME))
        //Action
        val result = usecase.execute(pokemonListMock, INCORRECT_SEARCH)
        //Check
        assertEquals(emptyList<Pokemon>(), result)
    }

    @Test
    fun `when execute is called with no search then returns pokemonList`() = runTest {
        //Prepare
        val pokemonListMock = listOf(Pokemon.mock(), Pokemon.mock().copy(name = POKEMON_NAME))
        //Action
        val result = usecase.execute(pokemonListMock, EMPTY_STRING)
        //Check
        assertEquals(pokemonListMock, result)
    }

    private companion object {
        const val POKEMON_NAME = "Charizard"
        const val CORRECT_SEARCH = "Cha"
        const val INCORRECT_SEARCH = "Chasdfghf"
    }
}