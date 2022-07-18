package com.bruno.pokedex.domain.usecase

import com.bruno.pokedex.domain.model.PokemonDetail
import com.bruno.pokedex.domain.model.PokemonPaginated
import com.bruno.pokedex.domain.repository.PokemonRepository
import com.bruno.pokedex.util.CoroutineTestRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class GetPokemonDetailUseCaseTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private val repository: PokemonRepository = mockk(relaxed = true)
    private lateinit var usecase: GetPokemonDetailUseCase

    @Before
    fun setup() {
        usecase = GetPokemonDetailUseCase(repository, coroutineTestRule.testDispatcher)
    }

    @Test
    fun `when execute is called then request is successful`() = runTest {
        //Prepare
        val resultMock = PokemonDetail.mock().copy(height = 1.5f, weight = 96.5f)
        coEvery { repository.getPokemonDetail(ID) } returns Result.success(PokemonDetail.mock())
        //Action
        val result = usecase.execute(ID)
        //Check
        coVerify(exactly = 1) { repository.getPokemonDetail(ID) }
        Assert.assertEquals(resultMock, result.getOrThrow())
    }

    @Test
    fun `when execute is called then request fails`() = runTest {
        //Prepare
        coEvery { repository.getPokemonDetail(ID) } returns Result.failure(Exception())
        //Action
        val result = usecase.execute(ID)
        //Check
        coVerify(exactly = 1) { repository.getPokemonDetail(ID) }
        Assert.assertTrue(result.isFailure)
    }

    private companion object {
        const val ID = 1
    }
}