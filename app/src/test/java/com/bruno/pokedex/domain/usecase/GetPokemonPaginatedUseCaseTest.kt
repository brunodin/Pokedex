package com.bruno.pokedex.domain.usecase

import com.bruno.pokedex.domain.model.PokemonPaginated
import com.bruno.pokedex.domain.repository.PokemonRepository
import com.bruno.pokedex.util.CoroutineTestRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class GetPokemonPaginatedUseCaseTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private val repository: PokemonRepository = mockk(relaxed = true)
    private lateinit var usecase: GetPokemonPaginatedUseCase

    @Before
    fun setup() {
        usecase = GetPokemonPaginatedUseCase(repository, coroutineTestRule.testDispatcher)
    }

    @Test
    fun `when execute is called then request is successful`() = runTest {
        //Prepare
        coEvery { repository.getPokemonPaginated(OFFSET) } returns Result.success(PokemonPaginated.mock())
        //Action
        val result = usecase.execute(PAGE)
        //Check
        coVerify(exactly = 1) { repository.getPokemonPaginated(OFFSET) }
        assertEquals(PokemonPaginated.mock(), result.getOrThrow())
    }

    @Test
    fun `when execute is called then request fails`() = runTest {
        //Prepare
        coEvery { repository.getPokemonPaginated(OFFSET) } returns Result.failure(Exception())
        //Action
        val result = usecase.execute(PAGE)
        //Check
        coVerify(exactly = 1) { repository.getPokemonPaginated(OFFSET) }
        assertTrue(result.isFailure)
    }

    private companion object {
        const val PAGE = 1
        const val OFFSET = 20
    }
}